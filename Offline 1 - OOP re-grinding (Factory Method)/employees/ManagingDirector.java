package employees;

import accounts.Account;
import exceptions.*;

public class ManagingDirector extends Employee {
    public ManagingDirector(String eName) {
        super(eName, EmployeeType.ManagingDirector );
    }

    /* A Managing Director can approve request and change interest rate as well. */
    public void approveRequest(Account account, double amount) throws BankExc {
        account.applyForLoan(amount);
    }

    public void changeInterestRate(Account account, double rate) throws DisallowedPermissionExc {
       account.getAccountType().setInterestRate(rate);
    }

}
