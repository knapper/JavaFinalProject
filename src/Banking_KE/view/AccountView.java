package Banking_KE.view;

import Banking_KE.model.*;

public class AccountView {
    public void displayMainMenu(){
        System.out.println("===== Welcome to the Banking System =====");
        System.out.println("Option we provide:");
        System.out.println("1. Rigister");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("=========================================");
        System.out.println("Please input your choice(1-3):");
    }

    public void displayUserMenu(){
        System.out.println("============== User Menu ================");
        System.out.println("Option we provide:");
        System.out.println("1. Check account information");
        System.out.println("2. Make a deposit");
        System.out.println("3. Make a withdraw");
        System.out.println("4. Change account information");
        System.out.println("5. Change password");
        System.out.println("6. Delete account");
        System.out.println("7. Logout");
        System.out.println("=========================================");
        System.out.println("Please input your choice(1-7):");
    }
    
    public void displayAccountDetail(Account account){
        System.out.println("Account Number: " + account.getAccountNumber() + " Client Name: " + account.getClientName() + " Balance: " + account.getBalance());
    }
}
