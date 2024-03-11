public class ImposterDecorator extends Decorator{
    private final Passenger passenger;

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[36m";

    public ImposterDecorator(Passenger passenger) {
        this.passenger = passenger;
    }
    public void login()
    {
        passenger.login();
        System.out.println(ANSI_RED+"We won't tell anyone; you are an imposter."+ANSI_RESET);
    }

    public void repair()
    {
        passenger.repair();
        System.out.println(ANSI_RED+"Damaging the spaceship."+ANSI_RESET);
    }

    public void work() throws InterruptedException {
        passenger.work();
        System.out.println(ANSI_RED+"Trying to kill a crewmate...");
        Thread.sleep(1200);
        System.out.println("Successfully killed a crewmate."+ANSI_RESET);
    }

    public void logout()
    {
        passenger.logout();
        System.out.println(ANSI_RED+"See you again Comrade Imposter."+ANSI_RESET);
    }
}
