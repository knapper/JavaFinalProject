package Banking.controller;

import java.sql.SQLException;
import java.util.Scanner;

import Banking.model.*;
import Banking.service.*;
import Banking.session.*;
import Banking.view.*;

public class AccountController_CMD {
    private AccountService accountService;
    private AccountView_CMD accountView;

    private UserSession userSession; // store the current user's information
    private Scanner scanner; // to read user input

    public AccountController_CMD(AccountService accountService, AccountView_CMD accountview){
        this.accountService = accountService;
        this.accountView = accountview;
        this.scanner = new Scanner(System.in);
    }

    // run the application
    public void run(){ // navigate the user, main menu
        // read user input, endless loop
        while (true){
            if (userSession == null){ // userSession is null, user is not logged in or registered -> show the main menu
                userSession = mainMenu();
            }else{ // userSession nuo null, user already logged in or registered -> show the user menu
                userSession = userMenu(userSession);
            }
        }      
    }
// main menu=================================================================================================
    public UserSession mainMenu(){
        while(true) {
            accountView.displayMainMenu(); // display the main menu options
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer
            switch(choice) {
                case 1:
                    return register();  // break 
                case 2:
                    return logIn(); // break
                case 3:
                    System.out.println("Thank you for using the Banking System, goodbye!");
                    System.exit(0); // exit JVM
                default:
                    System.out.println("Invalid input, please try again");
            }
        }
    }

    // register
    public UserSession register(){
        System.out.println("===== Register =====");
        int accountNumber = 0;
        String clientName = "";
        String password = "";

        // read user input
        while(true){
            System.out.println("Please input the account number(6-digital,starts with 100000):");
            accountNumber = scanner.nextInt();
            scanner.nextLine(); // clear the buffer
            if (accountNumber <100000 && accountNumber > 999999){
                System.out.println("Invalid account number, please try again");
                continue;
            }else{
                break;
            }
        }
        while(true){
            System.out.println("Please input the client name:");
            clientName = scanner.nextLine();
            scanner.nextLine(); // clear the buffer
            if (clientName.length() == 0){
                System.out.println("Invalid client name, please try again");
                continue;
            }else{
                break;
            }
        }
        while(true){
            System.out.println("Please input the password(4-digital):");
            password = scanner.nextLine();
            scanner.nextLine(); // clear the buffer
            if (password.length() != 4){
                System.out.println("Invalid password, please try again");
                continue;
            }else{
                break;
            }
        }
        Account newAccount = new Account(accountNumber, clientName, password);

        try{
            userSession = accountService.createAccount(newAccount);
            if (userSession != null){
                System.out.println("Account created successfully!");
            }else{
                System.out.println("Account creation failed, please try again");
            }
        }catch (SQLException e){
            System.out.println("Something went wrong when creating the account" + e);
        }
        
        return userSession;
    }

    // login
    public UserSession logIn(){
        System.out.println(" ===== Login =====");
        int accountNumber = 0;
        String password = "";

        // read user input
        while(true){
            System.out.println("Please input the account number(6-digital,starts with 100000):");
            accountNumber = scanner.nextInt();
            scanner.nextLine(); // clear the buffer
            if (accountNumber < 100000 || accountNumber > 999999){
                System.out.println("Invalid account number, please try again");
                continue;
            }else{
                break;
            }
        }
        while(true){
            System.out.println("Please input the password(4-digital):");
            password = scanner.nextLine();
            scanner.nextLine(); // clear the buffer
            if (password.length() != 4){
                System.out.println("Invalid password, please try again");
                continue;
            }else{
                break;
            }
        }

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
    public UserSession userMenu(UserSession userSession){
        while(true){
            accountView.displayUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            switch(choice){
                case 1:
                   getAccountDetail(userSession);
                   return userSession;
                case 2:
                    makeDeposit(userSession);
                    return userSession;
                case 3:
                    makeWithdrawal(userSession);
                    return userSession;
                case 4:
                    updateAccountInfo(userSession);
                    return userSession;
                case 5:
                    updatePassword(userSession);
                    return userSession;
                case 6: 
                    deleteAccount(userSession);
                    return null;
                case 7:
                    logOut(userSession);
                    return null;
                default:
                    System.out.println("Invalid input, please try again");
            }
        }
    }

    // get account details
    public void getAccountDetail(UserSession userSession){
        System.out.println("===== Account Details =====");
        try{
            Account account = accountService.getAccountDetail(userSession);
            accountView.displayAccountDetail(account);
        }catch(SQLException e){
            System.out.println("Something went wrong when getting the account details" + e);
        }
    }

    // make a deposit
    public void makeDeposit(UserSession userSession){
        double amount = 0;
        while(true){
            System.out.println("===== Make a Deposit =====");
            System.out.println("Please input the amount you want to deposit:");
            amount = scanner.nextDouble();
            scanner.nextLine(); // clear the buffer
            if (amount < 0){
                System.out.println("Invalid amount, please try again");
            }else{
                break;
            }
        }
        try{
            accountService.makeDeposit(userSession, amount);
            System.out.println("Deposit successfully!");
        }catch(SQLException e){
            System.out.println("Something went wrong when making a deposit" + e);
        }
    }

    // make a withdrawal
    public void makeWithdrawal(UserSession userSession){
        double amount = 0;
        while(true){
            System.out.println("===== Make a Withdrawal =====");
            System.out.println("Please input the amount you want to withdraw:");
            amount = scanner.nextDouble();
            scanner.nextLine(); // clear the buffer
            if (amount < 0){
                System.out.println("Invalid amount, please try again");
            }else{
                break;
            }
        }
        try{
            boolean result = accountService.makeWithdrawal(userSession, amount);
            if (result == true){
                System.out.println("Withdrawal successfully!");
            }else{
                System.out.println("Current balance is not enough for this withdrawal");
            }
        }catch (SQLException e){
            System.out.println("Something went wrong when making a withdrawal" + e);
        }
    }

    // update account information
    public void updateAccountInfo(UserSession userSession){
        System.out.println("===== Update Account Information =====");
        String newName = "";
        while(true){
            System.out.println("Please input the new client name:");
            newName = scanner.nextLine();
            scanner.nextLine(); // clear the buffer
            if (newName.length() == 0){
                System.out.println("Invalid client name, please try again");
            }else{
                break;
            }
        }
        try{
            accountService.updateAccountInfo(userSession, newName);
            System.out.println("Account information updated successfully!");
        }catch(SQLException e){
            System.out.println("Something went wrong when updating the account information" + e);
        }
    }

    // update password
    public void updatePassword(UserSession userSession){
        System.out.println("===== Update Password =====");
        String newPassword = "";
        while(true){
            System.out.println("Please input the new password:");
            newPassword = scanner.nextLine();
            scanner.nextLine(); // clear the buffer
            if (newPassword.length() != 4){
                System.out.println("Invalid password, please try again");
            }else{
                break;
            }
        }
        try{
            accountService.updatePassword(userSession, newPassword);
            System.out.println("Password updated successfully!");
        }catch(SQLException e){
            System.out.println("Something went wrong when updating the password" + e);
        }
    }

    // delete an account
    public void deleteAccount(UserSession userSession){
        System.out.println("===== Delete Account =====");
        try{
            accountService.deleteAccount(userSession);
            System.out.println("Account deleted successfully!");
        }catch(SQLException e){
            System.out.println("Something went wrong when deleting the account" + e);
        }
    }

    // logout
    public void logOut(UserSession userSession){
        System.out.println("===== Logout =====");
        userSession.setAccount(null);
        System.out.println("Logout successfully!");
    }
}
