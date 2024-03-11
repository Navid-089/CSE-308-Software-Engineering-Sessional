import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {

    private String type;
    private  User user ;

    private String stockName;
    private ArrayList<Stock> response ;

    public Request(String type,User user,String stockName){
        this.type = type ;
        this.user = user ;
        this.stockName = stockName;
        response = new ArrayList<>();
    }

    public String getType(){return type;}

    public User getUser(){ return user;}

    public String getStockName(){return stockName;}

    public void setResponse(ArrayList<Stock> response) { this.response =  response;}

    public ArrayList<Stock> getResponse() { return this.response ;}

}
