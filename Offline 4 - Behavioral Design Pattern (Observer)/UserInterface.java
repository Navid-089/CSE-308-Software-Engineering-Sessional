import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface implements Serializable {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        User user = null ;
        HashMap<String , User> loggedInList = new HashMap<>();

        while(true){
            String[] input = scanner.nextLine().split(" ");
            if(input[0].equals("login")) {
                if(user == null) {
                    if(loggedInList.get(input[1])==null){
                        user = new User(input[1], 3000);
                        user.logIn();
                        loggedInList.put(input[1] , user);
                    }
                    else {
                        user = loggedInList.get(input[1]);
                        user.logIn();
                    }
                }
                else System.out.println("already Logged in as ");
            }
            else if(input[0].equals("S")) {
                if(user!= null) user.subscribe(input[1] , 3000);
                else System.out.println("login first");
            }
            else if(input[0].equals("v")) {
                if(user!= null) user.viewSubscription(3000);
                else System.out.println("login first");
            }
            else if(input[0].equals("U")) {
                if(user!= null) user.unsubscribe(input[1] , 3000);
                else System.out.println("login first");
            } else if (input[0].equals("logout")) {
                user.logOut();
                user=null;
            }
        }
    }
}

