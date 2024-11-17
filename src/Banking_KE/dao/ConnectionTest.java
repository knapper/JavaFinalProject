package Banking_KE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/Banking";
    String user = "root";
    String password = "Zk48658345@1996";

    try (Connection connection = DriverManager.getConnection(url, user, password)) {
        if (connection != null) {
            System.out.println("Connected to the database successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
