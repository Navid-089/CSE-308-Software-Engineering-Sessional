package Builder;

import Ingredients.*;

public class Drink {

    private String name ;
    private double basePrice ;
    private double price ;
    private final Milk milk;
    private final Sweetener sweetener;
    private final Syrup syrup;
    private final IceCream iceCream;
    private final JelloItem jelloItem;
    private final Flavouring flavouring;
    private final Coffee coffee;
    private boolean candy ;
    private boolean cookies ;


    public Drink(Milk milk, Sweetener sweetener, Syrup syrup, IceCream iceCream, JelloItem jelloItem, Flavouring flavouring, Coffee coffee, boolean candy, boolean cookies) {
        this.milk = milk;
        this.sweetener = sweetener;
        this.syrup = syrup;
        this.iceCream = iceCream;
        this.jelloItem = jelloItem;
        this.flavouring = flavouring;
        this.coffee = coffee;
        this.candy = candy;
        this.cookies = cookies;
    }

    public Milk getMilk(){
        return this.milk ;
    }

    public Sweetener getSweetener() { return this.sweetener ;}

    public Syrup getSyrup() { return this.syrup;}

    public IceCream getIceCream(){ return this.iceCream;}

    public Flavouring getFlavouring() { return  this.flavouring;}

    public JelloItem getJelloItem() { return  this.jelloItem;}

    public Coffee getCoffee() { return this.coffee;}

    public boolean getCandy(){ return this.candy ;}

    public boolean getCookies(){ return this.cookies ;}
    public void setPrice(double price) {  this.price = price ;}
    public void setBasePrice(double price) { basePrice  = price;}
    public void setName(String name) { this.name = name ;}

    @Override
    public String toString(){

        String output =  this.name + " \n";
        if(milk == Milk.Regular_Milk) output = output+ " -Milk : " + milk ;
        output = output+ ("\n -Sugar/Sweetener : " + sweetener) ;

        if(syrup != null) output = output+  ("\n -Syrup : " + getSyrup());
        if(jelloItem != null) output = output+  ("\n -jello Item : " + getJelloItem());
        if(iceCream != null) output = output+  ("\n -Ice Cream : " + getIceCream());
        if(flavouring != null) output = output+  ("\n -Flavouring : " + getFlavouring());
        if(coffee != null) output = output+  ("\n -Coffee : " + getCoffee());

        output = output+ ("\n Base price : " + this.basePrice +" Tk\n");
        output = output+ ("\nAdd-Ons : \n" );
        if(milk == Milk.Almound_Milk) output+= (" -Milk : " + this.milk + " [+60 tk]\n");
        if(candy) output+= (" -Added Candy [+50 tk]\n");
        if(cookies) output+= (" -Added Cookies [+40 tk]\n");
        output += (" Increased price due to add-ons : "+ price +" Tk");
        return output;
    }

}

