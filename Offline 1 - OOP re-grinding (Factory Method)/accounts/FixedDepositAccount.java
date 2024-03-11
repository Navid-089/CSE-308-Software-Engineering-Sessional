package accounts;

import exceptions.*;
import helper.Helper;

import java.util.Date;

public class FixedDepositAccount extends Account {

    /*A fixed deposit account must ensure the first deposit is at least 100,000$. */


    public FixedDepositAccount(String name, double iniDeposit) throws InvalidAmountofMoneyExc {
        super(name, iniDeposit, AccountType.FIXED_DEPOSIT);
        if (iniDeposit < 100 * 1000)
            throw new InvalidAmountofMoneyExc("Invalid! The minimum initial deposit is 100,000$");
    }

    /* Maximum allowed loan for a fixed deposit account is 100,0000$ */
    public void applyForLoan(double amount) throws InvalidAmountofMoneyExc {
        checkAmount(amount);
        if (amount > 100 * 1000)
            throw new InvalidAmountofMoneyExc("Invalid! Maximum allowed loan for a fixed deposit account is 100,0000$");

        accountBalance += amount;
        loan += amount;

    }

    /* Minimum deposit is 50,000$ */
    public void deposit(double amount) throws InvalidAmountofMoneyExc {
        checkAmount(amount);

        if (amount < 50000) throw new InvalidAmountofMoneyExc("Invalid! The minimum deposit is 50,000$");
        this.accountBalance += amount;
    }

    /* A fixed deposit account cannot withdraw if it has not reached a maturity period of one
     year. */
    public void withdrawFromDeposit(double amount) throws BankExc {
        checkAmount(amount);
        if (accountBalance < amount)
            throw new BalanceShortageExc("Insufficient Balance!");
        Date currentDate = new Date(System.currentTimeMillis());
        int passedDays = Helper.differenceBetweenDays(openingDate, currentDate);

        if (passedDays < 365)
            throw new BankExc("Account has not reached a maturity period of one year yet. Please, try later.");

        accountBalance -= amount;
    }
}

