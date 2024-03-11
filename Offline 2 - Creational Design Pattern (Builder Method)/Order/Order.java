package Order;

import Builder.*;

import java.util.ArrayList;

public class Order {
    private final int orderID ;
    private ArrayList<Drink> orderList ;

    public Order(int orderID){
        this.orderID = orderID ;
        orderList = new ArrayList<>();
    }

    public int getOrderID() {
        return this.orderID ;
    }

    public int getTotalItems() { return orderList.size();}


    public void addItem(Drink drink){
        orderList.add(drink);
    }

    public void seeConfirmedOrder(){
        System.out.println("Order ID : " + this.orderID);
        System.out.println("------------------------------------");
        int item = 1 ;
        for(Drink currentDrink : orderList){
            System.out.print(item + " :");
            System.out.println(currentDrink);
            System.out.println();
            item++;
        }
    }
}
