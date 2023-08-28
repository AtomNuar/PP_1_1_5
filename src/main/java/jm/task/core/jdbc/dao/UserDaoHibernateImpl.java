package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = null;
    Connection connection = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
            String sqlCreateUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT(10) AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL," +
                    "age INT(10) NOT NULL)";
            Query query = session.createSQLQuery(sqlCreateUsersTable).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("OK creat");
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sqlDropUsersTable = "DROP TABLE IF EXISTS users";
        Query query = session.createSQLQuery(sqlDropUsersTable).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("from User")
                .getResultList();
        for (User u:users) {
            System.out.println(u);
        }
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sqlCleanUsersTable = "TRUNCATE TABLE users";
        Query query = session.createSQLQuery(sqlCleanUsersTable).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
