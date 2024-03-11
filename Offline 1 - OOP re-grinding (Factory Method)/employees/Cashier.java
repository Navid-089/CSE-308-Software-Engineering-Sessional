package employees;

import accounts.Account;
import exceptions.DisallowedPermissionExc;

public class Cashier extends Employee {
    public Cashier(String eName) {
        super(eName, EmployeeType.Cashier);
    }

    /* A cashier CANNOT approve request or change interest rate */

    public void approveRequest(Account account, double amount) throws DisallowedPermissionExc {
        throw new DisallowedPermissionExc();
    }
    public void changeInterestRate(Account account, double rate) throws DisallowedPermissionExc {
        throw new DisallowedPermissionExc();
    }
}
