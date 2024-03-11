import java.util.Scanner;

import exceptions.BankExc;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();
        String tmp = "";

        Scanner scnr = new Scanner(System.in);

        while (scnr.hasNext()) {
            String[] input = scnr.nextLine().split(" ");
            try {
                if (input[0].equalsIgnoreCase("Exit"))
                    break;
                switch (input[0]) {
                    case "Create":
                        bank.createAccount(input[1], Double.parseDouble(input[3]), input[2]);
                        tmp = input[1];
                        break;
                    case "Open":
                        bank.open(input[1]);
                        tmp = input[1];
                        break;
                    case "Close":
                        bank.close(tmp);
                        break;
                    case "INC":
                        bank.incrementYear();
                        break;
                    case "Deposit":
                        bank.depositIntoAccount(tmp, Double.parseDouble(input[1]));
                        break;
                    case "Request":
                        bank.applyForLoan(tmp, Double.parseDouble(input[1]));
                        break;
                    case "Withdraw":
                        bank.withdrawFromAccount(tmp, Double.parseDouble(input[1]));
                        break;
                    case "Query":
                        bank.seeDeposit(tmp);
                        break;
                    case "Lookup":
                        bank.lookup(tmp, input[1]);
                        break;
                    case "Change":
                        bank.changeInterestRate(tmp, input[1], Double.parseDouble(input[2]));
                        break;
                    case "Approve":
                        bank.approveLoan(tmp);
                        break;
                    case "See":
                        bank.seeInternalFund(tmp);
                        break;
                    default:
                        System.out.println("Invalid input given! Please try again.");
                        break;
                }
            } catch (BankExc e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error");
            }
        }
    }
}