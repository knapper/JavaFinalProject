package Banking_KE.service;

import Banking_KE.dao.*;
import Banking_KE.model.*;
import Banking_KE.session.*;

import java.sql.SQLException;

public class AccountService {
    // this class will contain the business logic of the application
    // it will call the DAO class to perform the CRUD operations, and return the results to the controller
    private BankingDAO bankingDAO;

    public AccountService(BankingDAO bankingDAO){
        this.bankingDAO = bankingDAO;
    }

// ====================================================================================================
// part 1: check the account, based on the .getAccount() method in AccountDAOImpl.java
    // check if the account exists
    public boolean checkAccountExists(int accountNumber) throws SQLException{
        Account account = bankingDAO.getAccount(accountNumber);
        if (account != null){
            return true; // if the account exists, return true
        }else{
            return false; // if the account does not exist, return false
        }
    }

    //validate the passward
    public boolean validatePassword(int accountNumber, String password) throws SQLException{
        Account account = bankingDAO.getAccount(accountNumber);
        if (password.equals(account.getPassword())){
            return true; // if the password is correct, return true
        }else {
            return false; // if the password is incorrect, return false
        }
    }

// ====================================================================================================
// part 2: account operations
// return the current user's information in UserSession object
    // part 2.1: register, based on the .createAccount() method in AccountDAOImpl.java
    public UserSession createAccount(Account newAccount) throws SQLException{
        boolean isExists = checkAccountExists(newAccount.getAccountNumber()); // check if the account exists
        if (isExists){
            System.out.println("The account already exists.");
            return null; // if the account exists, return null, end the operation
        }
        bankingDAO.createAccount(newAccount); // create the account
        UserSession userSession = new UserSession(newAccount); // create a new user session
        return userSession; // store the current user's information in UserSession
    }

    // part 2.2: Login, based on the .getAccount() method in AccountDAOImpl.java
    public UserSession logIn(int accountNumber, String password) throws SQLException{
        boolean isExists = checkAccountExists(accountNumber); // check if the account exists
        if (!isExists){
            System.out.println("The account does not exist.");
            return null; // if the account does not exist, return null, end the operation
        }
        boolean isValidPassword = validatePassword(accountNumber, password); // validate the password
        if (!isValidPassword){
            System.out.println("The password is invalid.");
            return null; // if the password is invalid, return null, end the operation
        }
        Account account = bankingDAO.getAccount(accountNumber); // get the account details
        UserSession userSession = new UserSession(account); // create a new user session
        return userSession; // store the current user's information in UserSession
    }

//  ====================================================================================================
// part 3: account operations, based on UserSession object from part 2
    public boolean updateAccountInfo(UserSession usersession, String newName) throws SQLException{
        Account currentAccount = usersession.getAccount(); // get the current account object
        int currentAccountNumber = currentAccount.getAccountNumber(); // get the current account number

        bankingDAO.updateAccountInfo(currentAccountNumber, newName); // update the database

        currentAccount.setClientName(newName); // update the current account object
        usersession.setAccount(currentAccount); // update the user session object

        return true;
    }

    // update password
    public boolean updatePassword(UserSession userSession, String newPassword) throws SQLException{
        Account currentAccount = userSession.getAccount();
        int currentAccountNumber = currentAccount.getAccountNumber(); 

        bankingDAO.updatePassword(currentAccountNumber, newPassword); // update the database

        currentAccount.setPassword(newPassword); // update the current account object
        userSession.setAccount(currentAccount); // update the user session object

        return true;
    }

    // update balance
    // make a deposit
    public boolean makeDeposit(UserSession userSession, double amount) throws SQLException{
        Account currentAccount = userSession.getAccount();
        int currentAccountNumber = currentAccount.getAccountNumber();
        double currentBalance = currentAccount.getBalance();

        double newBalance = currentBalance + amount;

        bankingDAO.updateBalance(currentAccountNumber, newBalance); // uodate the database

        currentAccount.setBalance(newBalance); // update the current account object
        userSession.setAccount(currentAccount); // update the user session object

        return true;
    }
    // make a withdrawal
    public boolean makeWithdrawal(UserSession userSession, double amount) throws SQLException{
        Account currentAccount = userSession.getAccount();
        int currentAccountNumber = currentAccount.getAccountNumber();
        double currentBalance = currentAccount.getBalance();

        if (currentBalance >= amount){
            double newBalance = currentBalance - amount;
            bankingDAO.updateBalance(currentAccountNumber, newBalance); // uodate the database
            
            currentAccount.setBalance(newBalance); // update the current account object
            userSession.setAccount(currentAccount); // update the user session object
            return true;
        }else {
            return false;
        }
    }

    // delete an account
    public boolean deleteAccount(UserSession userSession) throws SQLException{
        Account currentAccount = userSession.getAccount();
        int currentAccountNumber = currentAccount.getAccountNumber();

        bankingDAO.deleteAccount(currentAccountNumber); // delete the account from the database
        
        userSession.setAccount(null); // set the user session object to null

        return true;
    }

    // get account details
    public Account getAccountDetail(UserSession userSession) throws SQLException{
        Account currentAccount = userSession.getAccount();
        int currentAccountNumber = currentAccount.getAccountNumber();

        Account account = bankingDAO.getAccount(currentAccountNumber);
        return account;
    }
}
