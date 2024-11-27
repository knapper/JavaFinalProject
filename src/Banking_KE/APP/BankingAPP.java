package Banking_KE.APP;
import Banking_KE.controller.*;
import Banking_KE.dao.*;
import Banking_KE.service.*;
import Banking_KE.view.*;
public class BankingAPP {
    public static void main(String[] args) {
        BankingDAO bankingDAO = new AccountDAOImpl();
        AccountService accountService = new AccountService(bankingDAO);
        AccountView accountView = new AccountView();
        AccountController accountController = new AccountController(accountService,accountView);

        accountController.navigateToMainMenu();
    }
}
