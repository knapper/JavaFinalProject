package Banking_KE.dao;
import java.sql.*;
public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/Banking"; // create a string variable to store the url
    private static String userName = "root"; // create a string variable to store the username
    private static String password = "Zk48658345@1996"; // create a string variable to store the password

    // connect to the database
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }
}
