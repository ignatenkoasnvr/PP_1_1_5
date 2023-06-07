package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userServiceImp = new UserServiceImpl();

        userServiceImp.createUsersTable();

        userServiceImp.saveUser("Name1", "LastName1", (byte) 20);
        userServiceImp.saveUser("Name2", "LastName2", (byte) 25);
        userServiceImp.saveUser("Name3", "LastName3", (byte) 31);
        userServiceImp.saveUser("Name4", "LastName4", (byte) 38);

        userServiceImp.removeUserById(1);
        userServiceImp.getAllUsers();
        userServiceImp.cleanUsersTable();
        userServiceImp.dropUsersTable();
        }
}
