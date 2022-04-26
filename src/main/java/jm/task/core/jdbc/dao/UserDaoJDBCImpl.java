package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connect = new Util().getDbConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        final String command = "CREATE TABLE IF NOT EXISTS user (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL,age INT NOT NULL, PRIMARY KEY(id))";
        try (Statement statement = connect.createStatement()){
            statement.executeUpdate(command);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        final String command = "DROP TABLE IF EXISTS user";
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate(command);
            System.out.println("Удаление таблицы USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String command = "INSERT INTO user (name,lastName,age) VALUES(?,?,?)";
        try (PreparedStatement statement = connect.prepareStatement(command)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setInt(3,age);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        final String command = "DELETE FROM user WHERE id = ?;";
        try (PreparedStatement statement = connect.prepareStatement(command)){
            statement.setLong(1, id);
            System.out.println("Удален юзер с id");
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final List<User> userList = new ArrayList<>();
        final String command = "SELECT * FROM user";
        try (ResultSet set = connect.createStatement().executeQuery(command)){

            while (set.next()){
                User us = new User(set.getString("name"),
                        set.getString("lastName"),set.getByte("age"));
                us.setId(set.getLong("id"));
                userList.add(us);
            }
            System.out.println("Выгрузка в List");

        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        final String command = "TRUNCATE TABLE user";
        try (Statement statement = connect.createStatement()){
            statement.executeUpdate(command);
            System.out.println("Очистка таблицы");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
