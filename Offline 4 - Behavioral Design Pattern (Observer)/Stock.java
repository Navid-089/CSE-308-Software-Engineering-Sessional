import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {
    private String stockName;
    private int stockCount;
    private double stockPrice;

    ArrayList<User> subscribers ;

    public Stock( String stockName , int stockCount , double stockPrice){
        this.stockName = stockName ;
        this.stockCount = stockCount;
        this.stockPrice = stockPrice ;
        subscribers = new ArrayList<User>();
    }

    public int getStockCount() {    return stockCount;  }

    public void setStockCount(int stockCount) { this.stockCount = stockCount;   }

    public double getStockPrice() { return stockPrice;  }

    public void setStockPrice(double stockPrice) {  this.stockPrice = stockPrice;   }

    public String getStockName() {  return stockName;   }

    public void setStockName(String stockName) {    this.stockName = stockName; }

    public ArrayList<User> getSubscribers() { return subscribers; }

    @Override
    public String toString(){
        String info = "Stock Name : " + this.getStockName() + "\nStock Count : " + this.getStockCount() + "\nStock Price : " + this.getStockPrice();
        return  info ;
    }
}
