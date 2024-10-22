package Banking;

public class TransactionController {
	
    private AccountDAO accountDAO;
    private TransactionView transactionView;

    public TransactionController(AccountDAO accountDAO, TransactionView transactionView) {
        this.accountDAO = accountDAO;
        this.transactionView = transactionView;
    }

    public void createAccount(Account account) {
        accountDAO.createAccount(account);
        transactionView.displayMessage("Account created successfully: " + account);
    }

    public void makeDeposit(int accountNumber, double amount) {
        double currentBalance = accountDAO.getBalance(accountNumber);
        double newBalance = currentBalance + amount;
        accountDAO.updateBalance(accountNumber, newBalance);
        transactionView.displayMessage("Deposit of $" + amount + " successful. New balance: $" + newBalance);
    }

    public void makeWithdrawal(int accountNumber, double amount) {
        double currentBalance = accountDAO.getBalance(accountNumber);

        if (amount > currentBalance) {
            transactionView.displayMessage("Insufficient funds for withdrawal. Current balance: $" + currentBalance);
        } else {
            double newBalance = currentBalance - amount;
            accountDAO.updateBalance(accountNumber, newBalance);
            transactionView.displayMessage("Withdrawal of $" + amount + " successful. New balance: $" + newBalance);
        }
    }

    public void updateBalance(int accountNumber, double newBalance) {
        accountDAO.updateBalance(accountNumber, newBalance);
        transactionView.displayMessage("Balance updated successfully. New balance: $" + newBalance);
    }

    public double viewBalance(int accountNumber) {
        double balance = accountDAO.getBalance(accountNumber);
        transactionView.displayMessage("Current balance: $" + balance);
        return balance;
    }
}
