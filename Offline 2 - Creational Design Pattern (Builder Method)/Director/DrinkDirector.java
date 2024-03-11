package Director;

import Builder.*;
import Ingredients.*;

public class DrinkDirector {

    public static Drink createChocolateShake(DrinkBuilder builder , boolean lactoseFree , boolean candy ,boolean cookies) {

        builder.setSyrup(Syrup.Chocolate);
        builder.setIceCream(IceCream.Chocolate);
        builder.setSweetener(Sweetener.Sugar);

        double price = processAddOns(builder , lactoseFree , candy ,cookies , 230);
        Drink drink = builder.getDrink();
        drink.setPrice(price);
        drink.setBasePrice(230);
        drink.setName("Chocolate Shake");
        return drink;
    }

    public static Drink createCoffeeShake(DrinkBuilder builder , boolean lactoseFree , boolean candy ,boolean cookies ){

        builder.setCoffee(Coffee.Coffee);
        builder.setJelloItem(JelloItem.Jello);
        builder.setSweetener(Sweetener.Sugar);

        double price =processAddOns(builder , lactoseFree , candy ,cookies , 250);
        Drink drink = builder.getDrink();
        drink.setPrice(price);
        drink.setBasePrice(250);
        drink.setName("Coffee Shake");
        return drink;
    }

    public static Drink createStrawberryShake(DrinkBuilder builder , boolean lactoseFree , boolean candy ,boolean cookies ){

        builder.setSyrup(Syrup.Strawberry);
        builder.setIceCream(IceCream.StrawBerry);
        builder.setSweetener(Sweetener.Sugar);

        double price =processAddOns(builder , lactoseFree , candy ,cookies , 200);
        Drink drink = builder.getDrink();
        drink.setPrice(price);
        drink.setBasePrice(200);
        drink.setName("Strawberry Shake");
        return drink;
    }

    public static Drink createVanillaShake(DrinkBuilder builder , boolean lactoseFree , boolean candy ,boolean cookies ){
        builder.setJelloItem(JelloItem.Jello);
        builder.setFlavouring(Flavouring.vanilla);
        builder.setSweetener(Sweetener.Sugar);

        double price =processAddOns(builder , lactoseFree , candy ,cookies , 190);
        Drink drink = builder.getDrink();
        drink.setPrice(price);
        drink.setBasePrice(190);
        drink.setName("Vanilla Shake");
        return drink;
    }

    public static Drink createZeroShake(DrinkBuilder builder , boolean lactoseFree , boolean candy ,boolean cookies ){
        builder.setSweetener(Sweetener.Sweetener);
        builder.setFlavouring(Flavouring.vanilla);
        builder.setJelloItem(JelloItem.SugarFreeJello);

        double price =processAddOns(builder , lactoseFree , candy ,cookies , 240);
        Drink drink = builder.getDrink();
        drink.setPrice(price);
        drink.setBasePrice(240);
        drink.setName("Zero Shake");
        return drink;
    }


    private static double processAddOns(DrinkBuilder builder , boolean lactoseFree, boolean candy , boolean cookie , double price){
        if (lactoseFree) {
            builder.setMilk(Milk.Almound_Milk);
            price += 60;
        } else builder.setMilk(Milk.Regular_Milk);
        if (candy) {
            builder.addCandy();
            price += 50;
        }
        if (cookie) {
            builder.addCookies();
            price += 40;
        }
        return price;
    }

}
