package employees;

import accounts.Account;
import exceptions.*;

public class Officer extends Employee {
    public Officer(String eName) {
        super(eName, EmployeeType.Officer);
    }

    /* An Officer cannot change Interest Rate but approve Requests */
    public void approveRequest(Account account, double amount) throws BankExc {
        account.applyForLoan(amount);
    }
    public void changeInterestRate(Account account, double rate) throws DisallowedPermissionExc {
        throw new DisallowedPermissionExc();
    }

}
