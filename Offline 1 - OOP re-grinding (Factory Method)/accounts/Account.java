package accounts;

import exceptions.BankExc;
import exceptions.InvalidAmountofMoneyExc;

import java.util.Date;

public abstract class Account {
    private String ownerName;
    protected double accountBalance;
    protected double loan;
    protected Date openingDate;
    private AccountType accountType;


    public Account(String ownerName, double deposit, AccountType accountType) throws InvalidAmountofMoneyExc {
        if (deposit < 0)
            throw new InvalidAmountofMoneyExc("Deposit should be positive.");
        this.ownerName = ownerName;
        this.accountBalance = deposit;
        this.loan = 0;
        this.openingDate = new Date();
        this.accountType = accountType;
    }


    /* Abstract functions that children classes will define. */

    abstract public void applyForLoan(double amount) throws BankExc;

    abstract public void deposit(double amount) throws BankExc;

    abstract public void withdrawFromDeposit(double amount) throws BankExc;

    /* Getter-Setters */

    public String getOwnerName() {
        return ownerName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public double getLoan() {
        return loan;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getType() {
        if (accountType == AccountType.SAVINGS)
            return "Savings";
        else if (accountType == AccountType.STUDENT)
            return "Student";
        else if (accountType == AccountType.FIXED_DEPOSIT)
            return "Fixed_Deposit";
        return null;
    }


    /* Interest rates on deposit for savings, student, and fixed deposit accounts are 10%, 5%, 15%, respectively.
     All accounts except the student accounts will be deducted an amount of 500$ of service
      charge after one year.
    */

    public void applyYearlyInterestWithSC() {
        accountBalance += (accountBalance) * accountType.getInterestRate();     // Not sure if interest should be applied on the only deposit or (deposit + loan)
        chargeServiceExpense();
        double interestOfLoan = loan * 0.1;
        if (accountBalance > interestOfLoan) accountBalance -= interestOfLoan;
        else loan += interestOfLoan;
    }

    public void chargeServiceExpense() {
        if (accountType != AccountType.STUDENT) {
            if (accountBalance > 500) accountBalance -= 500;
        }
    }

    /*Checking the amount of transaction. It must be positive.*/
    public void checkAmount(double amount) throws InvalidAmountofMoneyExc {
        if (amount < 0)
            throw new InvalidAmountofMoneyExc("Invalid! Amount should be positive.");
    }

}