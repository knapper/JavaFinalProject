package Banking_KE.dao;
import java.sql.SQLException;

import Banking_KE.model.*;

// declare CRUD operations
public interface BankingDAO {
    public int createAccount(Account account) throws SQLException;
    public int updateAccountInfo(int accountNumber,String clientName) throws SQLException;
    public int updatePassword(int accountNumber, String password) throws SQLException;
    public int updateBalance(int accountNumber, double amount) throws SQLException;
    public int deleteAccount(int accountNumber) throws SQLException;
    public Account getAccount(int accountNumber) throws SQLException;
}
