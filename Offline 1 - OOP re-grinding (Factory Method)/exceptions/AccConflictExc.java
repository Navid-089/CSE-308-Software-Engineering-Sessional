package exceptions;

public class AccConflictExc extends BankExc {
    public AccConflictExc(){
        super("An Account with the same Name already exists! Please, try another name.");
    }
}
