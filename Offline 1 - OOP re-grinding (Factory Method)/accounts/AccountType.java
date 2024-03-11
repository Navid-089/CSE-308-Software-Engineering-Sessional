package accounts;

public enum AccountType {
    STUDENT("Student"),
    SAVINGS("Savings"),
    FIXED_DEPOSIT("FixedDeposit");

    private String type;
    private double interestRate;


    private AccountType(String type) {
        this.type = type;
        init(this.type);
    }

    private void init(String type) {
        switch (type) {
            case "Student":
                interestRate = 5.0 / 100.0;
                break;
            case "Savings":
                interestRate = 10.0 / 100.0;
                break;
            case "FixedDeposit":
                interestRate = 15.0 / 100.0;
                break;
            default:
                interestRate = 0.0;
                break;
        }
    }

    @Override
    public String toString() {
        return type;
    }

    /*Setters amd Getters*/

    public String getType() {
        return type;
    }
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double rate) {
        interestRate = rate / 100.0;
    }
}