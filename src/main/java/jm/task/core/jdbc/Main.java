package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getDbConnection();
        UserDao ud = new UserDaoJDBCImpl();

//        ud.createUsersTable();

//        ud.saveUser("Name1", "LastName1", (byte) 20);
//        ud.saveUser("Name2", "LastName2", (byte) 19);
//        ud.saveUser("Name3", "LastName3", (byte) 21);
//
//        ud.removeUserById(4);
//        ud.removeUserById(5);
//        ud.removeUserById(6);
//
//        ud.removeUserById(9);
//        ud.removeUserById(8);
//        ud.removeUserById(7);

//        System.out.println(ud.getAllUsers());
//       ud.cleanUsersTable();
        ud.dropUsersTable();
    }
}
