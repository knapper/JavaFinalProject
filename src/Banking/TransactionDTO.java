package Banking;

public class TransactionDTO {
    private int accountNumber;
    private double amount;

    // Constructor
    public TransactionDTO(int accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    // Getters and setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

