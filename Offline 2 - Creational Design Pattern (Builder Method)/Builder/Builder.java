package Builder;

import Ingredients.*;

interface Builder{
    public void setSyrup(Syrup syrupType);
    public void setSweetener(Sweetener sweetenerType);
    public void setMilk(Milk milkType);
    public void setJelloItem(JelloItem jelloType);
    public void setIceCream(IceCream iceCreamType);
    public void setFlavouring(Flavouring flavouringType);
    public void setCoffee(Coffee coffeeType);
    public void addCandy();
    public void addCookies();

}
