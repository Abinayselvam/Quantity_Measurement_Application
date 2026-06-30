package org.example.project.repository;

import org.example.project.entities.QuantityMeasurementEntity;
import org.example.project.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementJDBCRepository
        implements IQuantityMeasurementRepository {

    public QuantityMeasurementJDBCRepository() {

    }

    // ====================================================
    // Create Database if not exists
    // ====================================================

    private void createDatabase() {

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";

        try (Connection connection =
                     DriverManager.getConnection(url, user, password);

             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS quantitydb");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // ====================================================
    // Create Table
    // ====================================================

    private void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS quantity_measurement(
                 
                 id INT PRIMARY KEY AUTO_INCREMENT,
                 
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

            statement.execute(sql);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // ====================================================
    // INSERT
    // ====================================================

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = """
                INSERT INTO quantity_measurement(operation, operand1, operand2, result, is_error, error_message) VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(
                    1,
                    entity.getOperation());

            preparedStatement.setString(
                    2,
                    entity.getOperand1());

            preparedStatement.setString(
                    3,
                    entity.getOperand2());

            preparedStatement.setString(
                    4,
                    entity.getResult());

            preparedStatement.setBoolean(
                    5,
                    entity.isError());

            preparedStatement.setString(
                    6,
                    entity.getErrorMessage());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // ====================================================
    // READ
    // ====================================================

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM quantity_measurement";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                boolean error =
                        resultSet.getBoolean("is_error");

                QuantityMeasurementEntity entity;

                if (error) {

                    entity =
                            new QuantityMeasurementEntity(

                                    resultSet.getString("operation"),

                                    resultSet.getString(
                                            "error_message")
                            );

                } else {

                    entity =
                            new QuantityMeasurementEntity(

                                    resultSet.getString("operation"),

                                    resultSet.getString("operand1"),

                                    resultSet.getString("operand2"),

                                    resultSet.getString("result")
                            );
                }

                list.add(entity);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return list;
    }
    // ====================================================
    // FIND BY ID
    // ====================================================
    public QuantityMeasurementEntity findById(int id) {

        String sql =
                "SELECT * FROM quantity_measurement WHERE id=?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                boolean error =
                        resultSet.getBoolean("is_error");

                if (error) {

                    return new QuantityMeasurementEntity(

                            resultSet.getString("operation"),

                            resultSet.getString("error_message"));
                }

                return new QuantityMeasurementEntity(

                        resultSet.getString("operation"),

                        resultSet.getString("operand1"),

                        resultSet.getString("operand2"),

                        resultSet.getString("result"));
            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // ====================================================
    // UPDATE
    // ====================================================

    public void updateById(
            int id,
            QuantityMeasurementEntity  entity) {

        String sql =
                "UPDATE quantity_measurement\n" +
                        "SET\n" +
                        "operation=?,\n" +
                        "operand1=?,\n" +
                        "operand2=?,\n" +
                        "result=?,\n" +
                        "is_error=?,\n" +
                        "error_message=?\n" +
                        "WHERE id=?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, entity.getResult());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // ====================================================
    // DELETE
    // ====================================================

    public void deleteById(int id) {

        String sql =
                "DELETE FROM quantity_measurement WHERE id=?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}