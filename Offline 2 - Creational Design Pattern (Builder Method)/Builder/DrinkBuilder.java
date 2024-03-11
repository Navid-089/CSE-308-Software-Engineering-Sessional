package Builder;

import Ingredients.*;

public class DrinkBuilder implements Builder{

    private  Milk milk;
    private  Sweetener sweetener;
    private  Syrup syrup;
    private  IceCream iceCream;
    private  JelloItem jelloItem;
    private  Flavouring flavouring;
    private  Coffee coffee;
    private boolean candy ;
    private boolean cookies ;

    @Override
    public void setSyrup(Syrup syrupType) {
        this.syrup = syrupType ;
    }

    @Override
    public void setSweetener(Sweetener sweetenerType) {
        this.sweetener = sweetenerType;
    }

    @Override
    public void setMilk(Milk milkType) {
        this.milk = milkType ;
    }

    @Override
    public void setJelloItem(JelloItem jelloType) {
        this.jelloItem = jelloType ;
    }

    @Override
    public void setIceCream(IceCream iceCreamType) {
        this.iceCream = iceCreamType ;
    }

    @Override
    public void setFlavouring(Flavouring flavouringType) {
        this.flavouring = flavouringType ;
    }

    @Override
    public void setCoffee(Coffee coffeeType) {
        this.coffee = coffeeType ;
    }

    @Override
    public void addCandy() {
        candy = true ;
    }

    @Override
    public void addCookies() {
        cookies = true;
    }

    public Drink getDrink(){
        Drink createdDrink = new Drink(milk,sweetener,syrup,iceCream,jelloItem,flavouring,coffee,candy,cookies);
        this.reset();
        return  createdDrink;
    }

    private void reset(){
        this.milk = null;
        this.sweetener = null;
        this.syrup = null;
        this.iceCream = null;
        this.jelloItem = null;
        this.flavouring = null;
        this.coffee = null;
        this.candy = false;
        this.cookies = false;
    }
}
