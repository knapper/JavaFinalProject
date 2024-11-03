package DAOExample_Wine;
import java.sql.*;
public class DatabaseWine {
    private static Connection DBconnection = null;
    private static PreparedStatement SQLstatement = null;
    private static String url = "jdbc:mysql://localhost:3306/Wine";
    private static String userName = "root";
    private static String password = "Zk48658345@1996";

    // connect to the database
    public static Connection getConnection() throws SQLException{
        if (DBconnection == null){
        DBconnection = DriverManager.getConnection(url,userName,password);
        System.out.println("Connection established successfully");
        }else {
            System.out.println("Connection already exists");
        }
        return DBconnection;
    }

    // close the statement
    public static void closeStatement() throws SQLException{
        if (SQLstatement != null){
            SQLstatement.close();
        }else {
            System.out.println("Statement already closed");
        }
    }

    // close the connection
    public static void closeConnection() throws SQLException{
        if (DBconnection != null){
            DBconnection.close();
        }else {
            System.out.println("Connection already closed");
        }
    }
}
