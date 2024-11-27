package Banking_KE.model;

public class Account {
    private int accountNumber;
    private String clientName;
    private String password;
    private double balance = 0.00; // default balance
    //constructor1
    public Account(){
        
    }

    //constructor2
    public Account (int accountNumber, String clientName, String password){
        this.accountNumber = accountNumber;
        this.clientName = clientName;
        this.password = password;
    }

    //getter & setter

    public int getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getClientName(){
        return clientName;
    }
    public void setClientName(String clientName){
        this.clientName = clientName;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public String toString(){
        return "Account Number: " + accountNumber + "\n" +
                "Client Name: " + clientName + "\n" +
                "Balance: " + balance;
    }
}
