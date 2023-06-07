package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS my_db.users (id BIGINT not NULL AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT, PRIMARY KEY (id))";
        Transaction transaction = null;
        try (Session session = Util.getSession();) {
            User user = new User();
            transaction = session.beginTransaction();
            session.createNativeQuery(String.format(sql, user.getClass().getSimpleName())).executeUpdate();
            transaction.commit();
            System.out.println("DB was created");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS my_db.users";
        Transaction transaction = null;
        try (Session session = Util.getSession();) {
            User user = new User();
            transaction = session.beginTransaction();
            session.createNativeQuery(sql + user.getClass().getSimpleName()).executeUpdate();
            transaction.commit();
            System.out.println("Table was dropped");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, Byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSession();) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User with name: \"" + name + " " + lastName + "\" was added.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSession();) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User with id " + id + " was deleted.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List < User > result = new ArrayList< >();
        Transaction transaction = null;
        try (Session session = Util.getSession();) {
            transaction = session.beginTransaction();
            User user = new User();
            result.addAll(session.createQuery("FROM " + user.getClass().getSimpleName()).getResultList());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM my_db.users";
        Transaction transaction = null;
        try (Session session = Util.getSession();) {
            User user = new User();
            transaction = session.beginTransaction();
            session.createNativeQuery(sql + user.getClass().getSimpleName()).executeUpdate();
            transaction.commit();
            System.out.println("Table was clean.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }


    }
}
