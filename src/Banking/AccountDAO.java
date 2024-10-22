package Banking;

public interface AccountDAO {
    void createAccount(Account account);
    void updateBalance(int accountNumber, double newBalance);
    double getBalance(int accountNumber);
}

