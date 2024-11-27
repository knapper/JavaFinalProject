package Banking.APP;
import Banking.controller.*;
import Banking.dao.*;
import Banking.service.*;
import Banking.view.*;
public class BankingAPP {
    public static void main(String[] args) {
        BankingDAO bankingDAO = new AccountDAOImpl();
        AccountService accountService = new AccountService(bankingDAO);
        AccountView accountView = new AccountView();
        AccountController accountController = new AccountController(accountService,accountView);

        accountController.navigateToMainMenu();
    }
}
