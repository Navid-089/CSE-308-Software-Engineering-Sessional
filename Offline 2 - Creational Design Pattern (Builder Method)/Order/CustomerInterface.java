package Order;

import Builder.Drink;
import Builder.DrinkBuilder;
import Director.DrinkDirector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerInterface {

    public static void mainMenu(){
        System.out.println("1.Press 'o' to open an Order");
        System.out.println("2.Press 'exit' to halt the ordering process");
        System.out.print("Enter Your Choice : ");
    }

    public static void showDrinkMenu(){
        System.out.println();
        System.out.println("Shake Menu :");
        System.out.println("1. Chocolate Shake [230 tk]");
        System.out.println("2. Coffee Shake [250 Tk]");
        System.out.println("3. Strawberry Shake [200 Tk]");
        System.out.println("4. Vanilla Shake [190 Tk]");
        System.out.println("5. Zero Shake [240 Tk]");
        System.out.println("   Press 'e' to exit order");
        System.out.print("Enter your choice  : ");
    }

    public static boolean askLactoseFree(Scanner scanner){
        System.out.println("Do you want make it Lactose free? [ +60Tk ]");
        System.out.print("Enter choice (Y/N) : ");

        String choice = scanner.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
            System.out.print("Enter valid input : ");
            choice = scanner.next();
        }
        if(choice.equalsIgnoreCase("y")) return true;
        return false;
    }

    public static boolean askCandy(Scanner scanner){
        System.out.println("Do you want to add Candy Toppings ? [ +50Tk ]");
        System.out.print("Enter choice (Y/N) : ");
        String choice = scanner.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
            System.out.print("Enter valid input : ");
            choice = scanner.next();
        }
        if(choice.equalsIgnoreCase("y")) return true;
        return false;
    }

    public static boolean askCookies(Scanner scanner){
        System.out.println("Do you want to add Cookies Toppings ? [ +40Tk ]");
        System.out.print("Enter choice (Y/N) : ");
        String choice = scanner.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
            System.out.print("Enter valid input : ");
            choice = scanner.next();
        }
        if(choice.equalsIgnoreCase("y")) return true;
        return false;
    }

    public static Drink getDrink(DrinkBuilder builder ,int choice , boolean lactose , boolean candy , boolean cookies){
        System.out.println("Added to cart Successfully ! Would you Like to add something ?");
        if(choice == 1) return DrinkDirector.createChocolateShake(builder,lactose,candy,cookies);
        else if(choice == 2) return DrinkDirector.createCoffeeShake(builder,lactose,candy,cookies);
        else if(choice == 3) return DrinkDirector.createStrawberryShake(builder,lactose,candy,cookies);
        else if(choice == 4) return DrinkDirector.createVanillaShake(builder,lactose,candy,cookies);
        else if(choice == 5) return DrinkDirector.createZeroShake(builder,lactose,candy,cookies);
        return null ;
    }

    public static boolean confirmation(Scanner scanner){
        System.out.println("Do you want to exit Current order ? Your Order will be Confirmed!");
        System.out.print("Y/N :" );
        String choice  = scanner.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
            System.out.print("Enter valid input : ");
            choice = scanner.next();
        }
        if(choice.equalsIgnoreCase("y")) return true;
        else if(choice.equalsIgnoreCase("n")) return false;
        return confirmation(scanner);
    }

    public static boolean addConfirmedOrder(Order order , ArrayList<Order> orders ,int orderID){
        if(order.getTotalItems()>0){
            System.out.println("\norder Details :");
            order.seeConfirmedOrder();
            System.out.println("order Confirmed ! order ID : " + orderID);
            System.out.println();
            orders.add(order);
            return true;
        }
        else System.out.println("You must add at least one item !");
        return false;
    }

    public static void main(String[] args) {

        DrinkBuilder builder = new DrinkBuilder();                // drink builder
        ArrayList<Order> currentOrders = new ArrayList<>();       // confirmed orders
        int orderID = 1;                                          // order ID initialization
        Scanner scanner = new Scanner(System.in);                 // input scanner
        ArrayList<String> validInputs = new ArrayList<>(Arrays.asList("1","2","3","4","5","e","E"));


        CustomerInterface.mainMenu();

        while (true){

            String command = scanner.next();

            if(command.equalsIgnoreCase("o")){

                boolean lactoseFree = false;
                boolean cookies = false ;
                boolean candy = false ;
                Order order = new Order(orderID);

                CustomerInterface.showDrinkMenu();
                String choice = scanner.next();

                while (!validInputs.contains(choice)){
                    System.out.println("Input not recognised");
                    System.out.print("Enter your choice : ");
                    choice = scanner.next();
                }

                while (true){
                    if(!validInputs.contains(choice)){
                        System.out.print("please Enter valid Choice : ");
                        choice = scanner.next();
                        continue;
                    }

                    if(choice.equalsIgnoreCase("e")){
                        if(CustomerInterface.confirmation(scanner)){
                            if(CustomerInterface.addConfirmedOrder(order,currentOrders,orderID)){
                                orderID++;
                                break;
                            }
                            else {
                                CustomerInterface.showDrinkMenu();
                                choice = scanner.next();
                                continue;
                            }
                        }
                        else {
                            CustomerInterface.showDrinkMenu();
                            choice = scanner.next();
                            continue;
                        }
                    }
                    lactoseFree = CustomerInterface.askLactoseFree(scanner);
                    candy = CustomerInterface.askCandy(scanner);
                    cookies = CustomerInterface.askCookies(scanner);
                    Drink chosenDrink = CustomerInterface.getDrink(builder, Integer.parseInt(choice) , lactoseFree,candy,cookies);
                    order.addItem(chosenDrink);
                    CustomerInterface.showDrinkMenu();
                    choice = scanner.next();
                }
                CustomerInterface.mainMenu();

            }
            else if(command.equalsIgnoreCase("exit")){
                break;
            }
            else{
                System.out.println("Please enter a valid input !");
                System.out.println();
                CustomerInterface.mainMenu();
            }

        }

    }
}
