package Banking.session;

import Banking.model.Account;

public class UserSession { // use UserSession to store current user's information
    private Account currentaccount;

    public UserSession(Account currentaccount){
        this.currentaccount = currentaccount;
    }

    // getter and setter
    public Account getAccount(){
        return currentaccount;
    }

    public void setAccount(Account currentaccount){
        this.currentaccount = currentaccount;
    }
}
