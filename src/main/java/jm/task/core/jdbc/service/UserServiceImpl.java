package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import javax.transaction.Transactional;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDAO = new UserDaoHibernateImpl();

    @Override
    @Transactional
    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, Byte age) {
        userDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        System.out.println(users);
        return users;

    }

    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
