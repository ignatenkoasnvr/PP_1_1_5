package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import javax.transaction.Transactional;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl userDAOHibernateImpl = new UserDaoHibernateImpl();

    @Override
    @Transactional
    public void createUsersTable() {
        userDAOHibernateImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDAOHibernateImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, Byte age) {
        userDAOHibernateImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDAOHibernateImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userDAOHibernateImpl.getAllUsers();
        System.out.println(users);
        return users;

    }

    public void cleanUsersTable() {
        userDAOHibernateImpl.cleanUsersTable();
    }
}
