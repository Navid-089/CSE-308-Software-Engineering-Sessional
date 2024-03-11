public abstract class Decorator extends Passenger{
    abstract void login();
    abstract void logout();
    abstract void work() throws InterruptedException;
    abstract void repair();
}
