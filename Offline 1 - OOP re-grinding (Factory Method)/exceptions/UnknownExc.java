package exceptions;

public class UnknownExc extends BankExc{
    public UnknownExc()
    {
        super("Sorry, an unknown error occurred!");
    }
}