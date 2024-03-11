import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SystemAdmin extends Thread{

    private HashMap<String , Stock> stocks ;

    private HashMap<String , Socket> clients ;

    private HashMap<String , User> notifications ;
    final int PORT;
    ServerSocket serverSocket ;
    public SystemAdmin(int PORT){
        this.PORT = PORT;
        stocks = new HashMap<>();
        clients = new HashMap<>();
        this.loadStocks();
        notifications = new HashMap<>();

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        this.initServer(PORT);
    }

    public void increasePrice(String name, double price) throws IOException {
        Stock stock = stocks.get(name);
        stock.setStockPrice(stock.getStockPrice()+price);
        String msg = "Stock " + name + " price updated! Updated price : " +  stock.getStockPrice();
        this.notifySubscribers(name,msg);
    }

    public void decreasePrice(String name, double price) throws IOException {
        Stock stock = stocks.get(name);
        stock.setStockPrice(stock.getStockPrice()-price);
        String msg = "Stock " + name + " price updated! Updated price : " +  stock.getStockPrice();
        this.notifySubscribers(name ,msg);
    }

    public void changeStockCount(String name , int count) throws IOException {
        if(count<0){
            System.out.println("Stock counts must be non negative...");
            return ;
        }
        Stock stock = stocks.get(name);
        stock.setStockCount(count);
        String msg = "Stock " + name + " count updated! Updated count : " +  stock.getStockCount();
        this.notifySubscribers(name,msg);
    }

    public void addSubscriber (String name , User user){
        System.out.println(user.getname() + " subscribed to stock " + name );
        stocks.get(name).getSubscribers().add(user);
    }

    public void removeSubscriber(String name, User user){
        User toRemove = null;
        for(User users : stocks.get(name).getSubscribers()){
            if(users.getname().equals(user.getname())){
                toRemove = users;
            }
        }
        stocks.get(name).getSubscribers().remove(toRemove);
    }

    private void loadStocks() {             // load stocks from input file
        try (Scanner scanner = new Scanner(new FileInputStream(new File("init_stocks.txt")))) {
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                //System.out.println(currentLine);
                String[] lineTokens = currentLine.split(" ");
                Stock newStock = new Stock(lineTokens[0], Integer.parseInt(lineTokens[1]), Double.parseDouble(lineTokens[2]));
                stocks.put(lineTokens[0], newStock);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initServer(int PORT){
        try {
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("Server is online at port : " + PORT);

            while (true){
                Socket clientRequest = serverSocket.accept();
                Thread myThread = new Thread(() -> {
                    this.processRequest(clientRequest);
                });
                myThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(Socket clientRequest){

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientRequest.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientRequest.getOutputStream());
            Object request = objectInputStream.readObject();

            if(request instanceof Request currentRequest){

                if(currentRequest.getType().equalsIgnoreCase("S")) this.addSubscriber(currentRequest.getStockName(), currentRequest.getUser());
                if(currentRequest.getType().equalsIgnoreCase("U")) this.removeSubscriber(currentRequest.getStockName(), currentRequest.getUser());
                if(currentRequest.getType().equalsIgnoreCase("V")) objectOutputStream.writeObject(findStockDetails(currentRequest));
                if(currentRequest.getType().equalsIgnoreCase("update")) {
                    //System.out.println("update er age");
                    while(true) objectOutputStream.writeObject(findMessage(currentRequest));
                    //System.out.println("update er pore");
                }
            }
            if( request instanceof String clientName) {
                //System.out.println(clientName);
                this.clients.put(clientName, clientRequest);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> findMessage(Request request){
        //System.out.println("eita message");
        ArrayList<String> tosend = new ArrayList<>() ;
        String name = request.getUser().getname();
        User user = notifications.get(name);
        if(user == null) return null;
        if(user.getNotification().size() != 0) {

            for(String s : user.notification) tosend.add(s);
            user.getNotification().clear();
            return  tosend;
        }
        return null;
    }

    private Request findStockDetails(Request request){
        ArrayList<Stock> subscribedStocks = new ArrayList<>();
        for (Map.Entry<String, Stock> set : stocks.entrySet()) {
            for( User user : set.getValue().getSubscribers() ){
                if(user.getname().equals( request.getUser().getname())) subscribedStocks.add(set.getValue());
            }
        }
        request.setResponse(subscribedStocks);
        return request;
    }

    private void notifySubscribers(String stockName, String message) throws IOException {

        Stock stock = stocks.get(stockName);
        for(User users : stocks.get(stockName).getSubscribers()) {
            users.notification.add(message);
            //System.out.println(users.notification);
            //System.out.println(users.getname());
            notifications.put(users.getname() , users);
//            System.out.println(notifications.get(users.getname()));
        }
    }

    public static void main(String[] args) throws IOException {

        SystemAdmin systemAdmin = new SystemAdmin(3000);

        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            if(commands[0].equalsIgnoreCase("I")) systemAdmin.increasePrice(commands[1],Double.parseDouble(commands[2]));
            else if(commands[0].equalsIgnoreCase("D")) systemAdmin.decreasePrice(commands[1],Double.parseDouble(commands[2]));
            else if(commands[0].equalsIgnoreCase("C")) systemAdmin.changeStockCount(commands[1],Integer.parseInt(commands[2]));
        }
    }
}
