import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Among us!\n");

        while (true) {
            String userInput = scanner.nextLine();
            String[] inputs = userInput.split(" ");
            if (inputs.length == 1) {
                if (inputs[0].equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("Invalid input.");
                    continue;
                }
            }
            String instruction = inputs[0];
            String name = inputs[1];
            String role = name.substring(0, name.length() - 1);

            if (!instruction.equalsIgnoreCase("login"))
                break;
            Passenger passenger;
            switch (role) {
                case "crew":
                    passenger = new Crewmate();
                    break;
                case "imp":
                    passenger = new ImposterDecorator(new Crewmate());
                    break;
                default:
                    System.out.println("Invalid name!");
                    continue;
            }

            passenger.login();

            while (true) {
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase("repair"))
                    passenger.repair();
                else if (command.equalsIgnoreCase("work"))
                    passenger.work();
                else if (command.equalsIgnoreCase("logout")) {
                    passenger.logout();
                    break;
                } else if (command.equalsIgnoreCase("exit"))
                    return;
                else if (command.contains("login"))
                    System.out.println(name + " is already logged in. Please, logout first.");
                else
                    System.out.println("Invalid input!");
            }
        }

    }
}
