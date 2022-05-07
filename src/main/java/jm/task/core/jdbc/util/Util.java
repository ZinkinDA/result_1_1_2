package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Driver;


public class Util {
    private static final String dbUser = "root";
    private static final String dbPass = "assasin!23S!";
    private static final String dbName = "testdb";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static String connectString = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static Connection dbConnection;
    private static Driver dbDriver ;

    public static Connection getDbConnection(){
        try {
            //Class.forName();
            dbConnection = DriverManager.getConnection(connectString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    /***************************Hibernate*****************************/

    private static SessionFactory sessionFactory ;

    public static SessionFactory getSessionFactory(){

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", connectString)
                    .setProperty("hibernate.connection.username", dbUser)
                    .setProperty("hibernate.connection.password", dbPass )
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.c3p0.min_size","5")
                    .setProperty("hibernate.c3p0.max_size","200")
                    .setProperty("hibernate.c3p0.max_statements","200");
            ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(registry);
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return sessionFactory;
    }


}
