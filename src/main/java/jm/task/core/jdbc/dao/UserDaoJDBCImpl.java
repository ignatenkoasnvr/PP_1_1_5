package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private final Connection connection = Util.getConnection();


    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS my_db.users (id BIGINT not NULL AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT, PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
            System.out.println("Created table in database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS my_db.users";


        try (Statement statement = connection.createStatement();) {
            //connection.setAutoCommit(false);
            statement.executeUpdate(sql);
            System.out.println("Dropped table in my database.");
            //connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, Byte age) {
        String sql = "INSERT INTO my_db.users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User with name: \"" + name + " " + lastName + "\" added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Deleted user in my database, id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Statement statement = null;
        ResultSet resultSet;
        List<User> result = new ArrayList<>();
        try {connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM my_db.users");
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException ignored) {
            }
        }
        return result;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM my_db.users";
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
            System.out.println("Cleared table in database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
