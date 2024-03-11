package accounts;

import exceptions.*;
import helper.Helper;

import java.util.Date;

public class StudentAccount extends Account {
    public StudentAccount(String name, double iniDeposit) throws InvalidAmountofMoneyExc {
        super(name, iniDeposit, AccountType.STUDENT);
    }

    /* Maximum allowed loan for a student account is 1,000$ */
    public void applyForLoan(double amount) throws InvalidAmountofMoneyExc {
        checkAmount(amount);
        if (amount > 1000)
            throw new InvalidAmountofMoneyExc("Invalid! Maximum allowed loan for a student account is 1000$");

        accountBalance += amount;
        loan += amount;
    }

    public void withdrawFromDeposit(double amount) throws BankExc {
        checkAmount(amount);
        if (accountBalance < amount)
            throw new BalanceShortageExc("Insufficient Balance!");

        if (amount > 10 * 1000)
            throw new InvalidAmountofMoneyExc("Invalid transaction; current balance " + Helper.toCurrency(accountBalance));
        accountBalance -= amount;
    }

    public void deposit(double amount) throws InvalidAmountofMoneyExc {
        checkAmount(amount);
        this.accountBalance += amount;
    }
}