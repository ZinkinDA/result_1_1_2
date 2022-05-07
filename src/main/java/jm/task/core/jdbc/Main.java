package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();

        final User user1 = new User("Ivan", "Petrov", (byte) 30);
        final User user2 = new User("Petr", "Ivanov", (byte) 18);
        final User user3 = new User("John", "Smith", (byte) 46);
        final User user4 = new User("Harry", "Potter", (byte) 52);

            us.createUsersTable();

            us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

            us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

            us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

            us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

            us.getAllUsers();

            us.cleanUsersTable();

            us.dropUsersTable();
    }




}
