package org.example.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String SERVER_URL =
            "jdbc:mysql://localhost:3306/";

    private static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/quantitydb";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    // Used only for creating the database
    public static Connection getServerConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    SERVER_URL,
                    USER,
                    PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {

            throw new RuntimeException(
                    "Server Connection Failed : "
                            + e.getMessage());
        }
    }

    // Used after database is created
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    DATABASE_URL,
                    USER,
                    PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {

            throw new RuntimeException(
                    "Database Connection Failed : "
                            + e.getMessage());
        }
    }
}