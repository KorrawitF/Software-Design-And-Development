package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");
        try {
            Connection connection = DriverManager
                    .getConnection(""/*url db */, "", "");
            System.out.println("Database connected");
            Statement statement = connection.createStatement();
            ResultSet result = statement
                    .executeQuery("SELECT firstName, mi, lastName FROM Student WHERE lastName" + "='Smith'");
            while (result.next()) {
                System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Cannot connect to Database.");
        }
    }
}
