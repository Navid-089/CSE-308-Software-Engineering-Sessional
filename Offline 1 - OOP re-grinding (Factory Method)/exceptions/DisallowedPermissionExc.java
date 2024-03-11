package exceptions;

public class DisallowedPermissionExc extends BankExc{
    public DisallowedPermissionExc()
    {
        super("You are not allowed to perform this process.");
    }
}