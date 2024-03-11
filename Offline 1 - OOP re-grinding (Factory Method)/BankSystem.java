import accounts.Account;
import accounts.AccountCreation;
import employees.Employee;
import employees.EmployeeCreation;
import employees.EmployeeType;
import exceptions.AccConflictExc;
import exceptions.BankExc;
import exceptions.DisallowedPermissionExc;
import helper.Helper;

import java.util.HashMap;
import java.util.Map;

public class BankSystem {
    private int year;
    private double inFund;

    /* Mapping on keys to avoid collision. */
    private Map<String, Account> accountMap;
    private Map<Account, Double> loanMap;
    private Map<String, Employee> employeeMap;

    private AccountCreation accountCreation;
    private EmployeeCreation employeeCreation;

    public void putIntoEmployeeMap (String name, EmployeeType employeeType)
    {
        employeeMap.put(name,employeeCreation.createEmployee(name,employeeType));
    }


    public BankSystem() {
        inFund = 1000 * 1000;
        year = 0;

        accountMap = new HashMap<>();
        loanMap = new HashMap<>();
        employeeMap = new HashMap<>();

        accountCreation = new AccountCreation();
        employeeCreation = new EmployeeCreation();

        for (int i = 1; i <= 2; i++)
            putIntoEmployeeMap("O" + i, EmployeeType.Officer);
        for (int i = 1; i <= 5; i++)
            putIntoEmployeeMap("C" + i, EmployeeType.Cashier);
        putIntoEmployeeMap("MD",EmployeeType.ManagingDirector);

        System.out.println("Bank Created; MD, O1, O2, C1, C2, C3, C4, C5 created");
    }

    public void createAccount(String name, double amount, String accountType) throws BankExc {
        if (accountMap.containsKey(name))
            throw new AccConflictExc();
        Account account = accountCreation.createAccount(name, accountType, amount);
        if (account != null) {
            accountMap.put(name, account);
            String msg = account.getAccountType() + " account for " + account.getOwnerName() + " Created; initial deposit "
                    + Helper.toCurrency(account.getAccountBalance());
            System.out.println(msg);
            inFund += amount;
        } else {
            new BankExc("Failed to create Account.");
        }
    }

    /* Common Methods for both Employees and Accounts */

    public void incrementYear() {
        year++;
        System.out.println("1 year has passed.");
        for (Account acc : accountMap.values())
            acc.applyYearlyInterestWithSC();
    }

    public void open(String name) throws BankExc {
        if (accountMap.containsKey(name))
            System.out.println("Welcome back, " + name);
        else if (employeeMap.containsKey(name)) {
            Employee employee = employeeMap.get(name);
            if (employee.getEmployeeType() == EmployeeType.ManagingDirector || employee.getEmployeeType() == EmployeeType.Officer) {
                if (!loanMap.isEmpty())
                    System.out.println(name + " active, there are loan approvals pending");
                else
                    System.out.println(name + " active, no loan approvals pending");
            } else
                throw new BankExc("Invalid! Employee not found.");
        }
    }

    public void close(String name) throws BankExc {
        if (employeeMap.containsKey(name))
            System.out.println("Operations for " + name + " closed.");
        else if (accountMap.containsKey(name))
            System.out.println("Transaction of Account name " + name + " closed.");
        else
            throw new BankExc("Invalid usertype!");
    }

    /* Methods for Employees Only */
    public void lookup(String name, String accName) throws BankExc {
        if (employeeMap.containsKey(name)) {
            Employee employee = employeeMap.get(name);
            if (accountMap.containsKey(accName))
                employee.lookup(accountMap.get(accName));
            else
                throw new BankExc("Invalid! Bank account not found.");
        } else {
            throw new BankExc("Invalid! Employee not found.");
        }
    }

    public void approveLoan(String name) throws BankExc {
        if (employeeMap.containsKey(name)) {
            Employee employee = employeeMap.get(name);

            if (loanMap.isEmpty() == false) {
                for (Account acc : loanMap.keySet()) {
                    employee.approveRequest(acc, loanMap.get(acc));
                    System.out.println("Loan Request of " + acc.getOwnerName() + " approved.");
                }
            }
        } else {
            throw new BankExc("Invalid! Employee not found.");
        }
    }

    public void seeInternalFund(String name) throws BankExc {
        if (employeeMap.containsKey(name)) {
            Employee employee = employeeMap.get(name);
            if (employee.getEmployeeType() == EmployeeType.ManagingDirector)
                System.out.println("Internal fund: " + Helper.toCurrency(inFund));
            else
                throw new DisallowedPermissionExc();
        } else {
            throw new BankExc("You are not allowed to do this operation.");
        }
    }

    public void changeInterestRate(String name, String accountType, double rate) throws BankExc {
        if (employeeMap.containsKey(name)) {
            Employee employee = employeeMap.get(name);

            for (Account acc : loanMap.keySet()) {
                if (accountType.equals(acc.getType())) {
                    employee.changeInterestRate(acc, rate);
                    System.out.println("Changed interest rate to " + rate);
                }
            }
        }
    }

    public void seeDeposit(String name) throws BankExc {
        Account acc = accountMap.get(name);
        if (acc != null) {
            String loan = "loan " + Helper.toCurrency(acc.getLoan());
            String bal = "Current balance " + Helper.toCurrency((acc.getAccountBalance()));

            if (acc.getLoan() == 0)
                System.out.println(bal);
            else
                System.out.println(bal + ", " + loan);
        } else
            throw new BankExc("Invalid! Account not found.");
    }


    /* Methods for Accounts Only */

    public void applyForLoan(String name, double amount) throws BankExc {
        Account acc = accountMap.get(name);
        if (acc != null) {
            loanMap.put(acc, amount);
            System.out.println("Loan request successful; sent for approval.");
        } else
            throw new BankExc("Invalid! Account not found.");
    }

    public void withdrawFromAccount(String name, double amount) throws BankExc {
        Account acc = accountMap.get(name);
        if (acc != null) {
            acc.withdrawFromDeposit(amount);
            inFund -= amount;
            System.out.println(Helper.toCurrency(amount) + " withdrawn; current balance " + Helper.toCurrency(acc.getAccountBalance()));

        } else
            throw new BankExc("Invalid! Account not found.");
    }

    public void depositIntoAccount(String name, double amount) throws BankExc {
        Account account = accountMap.get(name);
        if (account != null) {
            account.deposit(amount);
            System.out.println(Helper.toCurrency(amount) + " deposited; current balance " + Helper.toCurrency(account.getAccountBalance()));
        } else
            throw new BankExc("Invalid! Account not found.");
    }
}