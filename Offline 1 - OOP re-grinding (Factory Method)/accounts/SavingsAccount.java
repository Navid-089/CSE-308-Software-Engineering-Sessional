package accounts;

import exceptions.*;
import helper.Helper;

import java.util.Date;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, double iniDeposit) throws InvalidAmountofMoneyExc {
        super(name, iniDeposit, AccountType.SAVINGS);
    }


    /* Maximum allowed loan for a savings account is 10,000$ */
    public void applyForLoan(double amount) throws InvalidAmountofMoneyExc {
        checkAmount(amount);
        if (amount > 10 * 1000)
            throw new InvalidAmountofMoneyExc("Invalid! Maximum allowed loan for a savings account is 10,000$");

        accountBalance += amount;
        loan += amount;
    }

    /* A savings account cannot withdraw if the withdrawal results in a deposit of less than 1,000$ */

    public void withdrawFromDeposit(double amount) throws BankExc {
        checkAmount(amount);
        if (accountBalance < amount)
            throw new BalanceShortageExc("Insufficient Balance!");

        if ((accountBalance - amount) < 1000)
            throw new InvalidAmountofMoneyExc("The withdrawal cannot result in a deposit of less than 1000$.");
        accountBalance -= amount;
    }

    public void deposit(double amount) throws InvalidAmountofMoneyExc {
        checkAmount(amount);
        this.accountBalance += amount;
    }
}
