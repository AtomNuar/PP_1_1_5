package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {

            String sqlCreateUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT(10) AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL," +
                    "age INT(10) NOT NULL)";
            statement.executeUpdate(sqlCreateUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sqlDropUsersTable = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sqlDropUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSaveUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlRemoveUserById = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveUserById)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sqlCleanUsersTable = "TRUNCATE TABLE users";
            statement.executeUpdate(sqlCleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
