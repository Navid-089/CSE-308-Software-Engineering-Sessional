package employees;

import accounts.*;
import exceptions.BankExc;
import helper.Helper;

public abstract class Employee {
    private String eName;
    protected EmployeeType employeeType;

    /* Abstract methods to changeInterestRate and approveRequest */
    public abstract void changeInterestRate(Account account, double rate) throws BankExc;

    public abstract void approveRequest(Account account, double amount) throws BankExc;

    public Employee(String eName, EmployeeType employeeType) {
        this.eName = eName;
        this.employeeType = employeeType;
    }

    /* Getters */
    public String getName() {
        return eName;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    /* All the employees can look up any account's balance. So, it is permitted for all. */
    public void lookup(Account account)
    {
        System.out.println(account.getOwnerName() + "'s current balance "+ Helper.toCurrency(account.getAccountBalance()));
    }

}