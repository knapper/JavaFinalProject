package Banking;

	
	
	public class Account {
	    private int accountNumber;
	    private String customerName;
	    private double balance;

	    // Constructor
	    public Account(int accountNumber, String customerName, double balance) {
	        this.accountNumber = accountNumber;
	        this.customerName = customerName;
	        this.balance = balance;
	    }

	    // Getters and setters
	    public int getAccountNumber() {
	        return accountNumber;
	    }

	    public void setAccountNumber(int accountNumber) {
	        this.accountNumber = accountNumber;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }

	    // toString method for easy printing of account details
	    @Override
	    public String toString() {
	        return "Account{" +
	                "accountNumber=" + accountNumber +
	                ", customerName='" + customerName + '\'' +
	                ", balance=" + balance +
	                '}';
	    }
	}

	


