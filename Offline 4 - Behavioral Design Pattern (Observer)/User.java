import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

interface INotify{
    void getMessage(ObjectOutputStream objectOutputStream , ObjectInputStream objectInputStream) throws Exception;
}

public class User extends Thread implements Serializable,INotify  {

    String name ;
    boolean online ;
    int PORT;
    transient Socket clientSocket;
    ArrayList<String> notification;

    public User(String name , int PORT){
        this.name = name ;
        notification = new ArrayList<>();
        this.PORT = PORT ;
    }

    public void subscribe (String stockName , int PORT) throws IOException {
        Socket socket = new Socket("localhost",PORT);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Request request = new Request("S",this,stockName);
        objectOutputStream.writeObject(request);
        socket.close();
    }
    public void unsubscribe (String stockName , int PORT) throws IOException {
        Socket socket = new Socket("localhost",PORT);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Request request = new Request("U",this,stockName);
        objectOutputStream.writeObject(request);
        socket.close();
    }

    public void viewSubscription (int PORT) throws Exception {

        Socket socket = new Socket("localhost",PORT);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Request request = new Request("V",this,"");
        objectOutputStream.writeObject(request);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object receivedResponse = objectInputStream.readObject();
        if(receivedResponse instanceof Request && ((Request)receivedResponse).getUser().getname().equals(this.getname())) {
            ArrayList<Stock> stocks = ( (Request) receivedResponse).getResponse();
            System.out.println(stocks.size());
            for(Stock stock : stocks) System.out.println(stock);
        }
        socket.close();
    }
    public void logIn() throws IOException {
        this.online = true ;
        if(this.clientSocket == null){
            this.clientSocket = new Socket("localhost",PORT);
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectOutputStream.writeObject(this.getname());

        if(this.notification.size() != 0) {
            for(String msg : notification) System.out.println(msg);
        }
        Thread t = new Thread(this);
        t.start();

    }

    public void logOut() throws IOException {
        this.online = false ;
    }

    public String getname() { return this.name;}

    @Override
    public void getMessage(ObjectOutputStream objectOutputStream , ObjectInputStream objectInputStream) throws Exception {

        Object receivedResponse = objectInputStream.readObject();
        ArrayList<String> response = (ArrayList<String>) (receivedResponse);
        //System.out.println(response);
        if(response != null) {
            if (response.size() > 0 && this.online) {
                for (String s : response) System.out.println(s);
            }
            if (response.size() > 0 && !this.online) this.notification = response;
        }

    }
    public ArrayList<String> getNotification() { return  this.notification ;}

    @Override public void run(){
        Socket socket ;
        ObjectInputStream objectInputStream = null ;
        ObjectOutputStream objectOutputStream = null ;
        Request request = new Request("update",this,"");
        try {
            socket = new Socket("localhost" , 3000);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while(true){
            try {
                getMessage(objectOutputStream , objectInputStream);
                //System.out.println(1);
            } catch (Exception e) { e.printStackTrace();
            }
        }
    }

}
