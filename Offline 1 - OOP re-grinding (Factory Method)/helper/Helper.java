package helper;

import java.util.Date;

public class Helper {

     public static String toCurrency(double money) {
        return String.format("%,.2f$", money);
    }

    public static int differenceBetweenDays(Date d1, Date d2) {
        long difference = d2.getTime() - d1.getTime();
        int numberOfYears = (int) difference / (1000 * 60 * 60 * 24);
        return numberOfYears;
    }


}