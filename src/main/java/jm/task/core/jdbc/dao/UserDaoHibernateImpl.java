package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS testdb.user (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL,age INT NOT NULL, PRIMARY KEY(id))");
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS testdb.user");
            transaction.commit();
            System.out.println("Таблица удалена!");
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.get(User.class, id));
//            session.createNativeQuery("");
            transaction.commit();
            System.out.println("User deleted!");
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaQuery <User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery(criteriaQuery).getResultList();

        try {
            transaction.commit();
            return list;
        } catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE testdb.user");
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
