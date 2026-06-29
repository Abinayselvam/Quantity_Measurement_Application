package org.example.project.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    public static void initialize() {

        createDatabaseIfNotExists();

        createTableIfNotExists();

        System.out.println("Database initialized successfully.");
    }

    private static void createDatabaseIfNotExists() {

        String sql =
                "CREATE DATABASE IF NOT EXISTS quantitydb";

        try (Connection connection =
                     DBConnection.getServerConnection();

             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate(sql);

            System.out.println("Database ready.");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    private static void createTableIfNotExists() {

        String sql = """
                CREATE TABLE IF NOT EXISTS quantity_measurement
                (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    
                    operation VARCHAR(50),
                    
                    operand1 VARCHAR(100),
                    
                    operand2 VARCHAR(100),
                    
                    result VARCHAR(100),
                    
                    is_error BOOLEAN,
                    
                    error_message VARCHAR(255)
                )
                """;

        try (Connection connection =
                     DBConnection.getConnection();

             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate(sql);

            System.out.println("Table ready.");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}