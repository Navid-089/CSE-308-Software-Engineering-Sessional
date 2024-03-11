package accounts;

import exceptions.InvalidAmountofMoneyExc;


/* An Account Factory */
public class AccountCreation {
    public Account createAccount(String name, String accountType, double iniDeposit) throws InvalidAmountofMoneyExc {
        switch (accountType) {
            case "Student":
                return new StudentAccount(name, iniDeposit);
            case "Savings":
                return new SavingsAccount(name, iniDeposit);
            case "FixedDeposit":
                return new FixedDepositAccount(name, iniDeposit);
            default:
                System.out.println("Invalid Account Type!");
                return null;
        }
    }
}