package Banking.controller;

import java.sql.SQLException;

import Banking.model.*;
import Banking.service.*;
import Banking.session.*;
import Banking.view.*;

public class AccountController {
    private AccountService accountService;
    private AccountView accountView;
    private UserSession userSession; // store the current user's information


    public AccountController(AccountService accountService, AccountView accountview){
        this.accountService = accountService;
        this.accountView = accountview;
    }

    // navigation ===============================================================================================
    public void navigateToMainMenu(){
        accountView.displayMainMenu(this);
    }

    public void navigateToRegister(){
        accountView.displayRegisterForm(this);
    }

    public void navigateToLogin(){
        accountView.displayLoginForm(this);
    }

    public void navigateToUserMenu(UserSession currrentUser){
        accountView.displayUserMenu(this,currrentUser);
    }


    // main menu=================================================================================================
    // register
    public UserSession handleRegister(int accountNumber, String clientName, String password){
        Account newAccount = new Account(accountNumber, clientName, password);
        try{
            userSession = accountService.createAccount(newAccount);
        }catch (SQLException e){
            System.out.println("Something went wrong when creating the account" + e);
        }
        
        return userSession;
    }

    // login
    public UserSession handleLogin(int accountNumber, String password){
        try{
            userSession = accountService.logIn(accountNumber, password);
            if (userSession != null){
                System.out.println("Login successfully!");
            }else{
                System.out.println("Login failed, please try again");
            }
        }catch(SQLException e){
            System.out.println("Something went wrong when logging in" + e);
        }
        
        return userSession;
    }
// user menu=================================================================================================
    // get account details
    public Account getAccountDetail(UserSession userSession){
        try{
            Account account = accountService.getAccountDetail(userSession);
            return account;
        }catch(SQLException e){
            System.out.println("Something went wrong when getting the account details" + e);
            return null;
        }
    }

    // make a deposit
    public boolean makeDeposit(UserSession userSession, double amount){
        try{
            if(accountService.makeDeposit(userSession, amount)){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            System.out.println("Something went wrong when making a deposit" + e);
            return false;
        }
    }

    // make a withdrawal
    public boolean makeWithdrawal(UserSession userSession, double amount){
        try{
            if (accountService.makeWithdrawal(userSession, amount)) {
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            System.out.println("Something went wrong when making a withdrawal" + e);
            return false;
        }
    }

    // update account information
    public boolean updateAccountInfo(UserSession currentSession, String clientName){
        try{
            if(accountService.updateAccountInfo(currentSession, clientName)){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            System.out.println("Something went wrong when updating the account information" + e);
            return false;
        }
    }

    // update password
    public boolean updatePassword(UserSession currentUser, String password){
        try{
            if(accountService.updatePassword(userSession, password)){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            System.out.println("Something went wrong when updating the password" + e);
            return false;
        }
    }

    // delete an account
    public boolean deleteAccount(UserSession userSession){
        try{
            if (accountService.deleteAccount(userSession)){
                return true;
            }else{ 
                return false;
            }
        }catch(SQLException e){
            System.out.println("Something went wrong when deleting the account" + e);
            return false;
        }
    }

    // logout
    public void logout(UserSession userSession){
        userSession.setAccount(null);
    }
}
