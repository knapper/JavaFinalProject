package Banking_KE.dao;
import java.sql.*;

import Banking_KE.model.*;
public class AccountDAOImpl implements BankingDAO{
    // implement the CRUD operations
    @Override
    public int createAccount(Account account) throws SQLException{
        String sqlStatement = "INSERT INTO Account (accountNumber, clientName, password) VALUES (?,?,?)";
        try( // try with resources, close the connection after the operation is done
            Connection connection = DBConnection.getConnection();
            PreparedStatement sql = connection.prepareStatement(sqlStatement);
        ){
            // set the values of sql statement
            sql.setInt(1, account.getAccountNumber());
            sql.setString(2, account.getClientName());
            sql.setString(3, account.getPassword());

            // run the sql statement
            int result = sql.executeUpdate();
            // return the result
            return result;
        }
    }

    @Override
    public int updateAccountInfo(int accountNumber, String newName) throws SQLException{
        String sqlStatement = "UPDATE Account SET clientName = ? WHERE accountNumber = ? ";
        try(
            Connection connection = DBConnection.getConnection();
            // create the sql statement
            PreparedStatement sql = connection.prepareStatement(sqlStatement);
        ){
            // set the values of the sql statement
            sql.setString(1, newName);
            sql.setInt(2, accountNumber);
            // run the sql statement
            int result = sql.executeUpdate();
            // return the result
            return result;
        }

    }

    @Override
    public int updateBalance(int accountNumber, double balance) throws SQLException{
        String sqlStatement = "UPDATE Account SET balance = ? WHERE accountNumber = ?";
        try(
            Connection connection = DBConnection.getConnection();
            // create the sql statement
            PreparedStatement sql = connection.prepareStatement(sqlStatement);
        ){
            // set the values of the sql statement
            sql.setDouble(1, balance);
            sql.setInt(2, accountNumber);

            // run the sql statement
            int result = sql.executeUpdate();
            // return the result
            return result;
        }
    }

    @Override
    public int updatePassword(int accountNumber, String newPassword) throws SQLException{
        String sqlStatement = "UPDATE Account SET password = ? WHERE accountNumber = ?";
        try(
            Connection connection = DBConnection.getConnection();
            PreparedStatement sql = connection.prepareStatement(sqlStatement);
        ){
            sql.setString(1, newPassword);
            sql.setInt(2, accountNumber);
    
            int result = sql.executeUpdate();
            return result;
        }
    }

    @Override
    public int deleteAccount(int accountNumber) throws SQLException{
        String sqlStatement = "DELETE FROM Account WHERE accountNumber = ?";
        try(
            Connection connection = DBConnection.getConnection();
            PreparedStatement sql = connection.prepareStatement(sqlStatement);
        ){
            sql.setInt(1, accountNumber);
            int result = sql.executeUpdate();
            return result;
        }
    }

    @Override
    public Account getAccount(int accountNumber) throws SQLException {
        String sqlStatement = "SELECT * FROM Account WHERE accountNumber = ?";
        try(
            Connection connection = DBConnection.getConnection();
            PreparedStatement sql = connection.prepareStatement(sqlStatement);
        ){
            sql.setInt(1, accountNumber);
            ResultSet result = sql.executeQuery();
    
            if (result.next()){
                Account account = new Account();
                account.setAccountNumber(result.getInt("accountNumber"));
                account.setClientName(result.getString("clientName"));
                account.setPassword(result.getString("password"));
                account.setBalance(result.getDouble("balance"));
                return account;
            }else {
                return null;
            }
        }
    }
}
