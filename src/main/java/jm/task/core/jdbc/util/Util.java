package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import java.util.Properties;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;
import javax.persistence.Transient;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String DB_USERNAME = "bestuser";
    private static final String DB_PASSWORD = "bestuser";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";


//
//    private static final String Driver = "com.mysql.cj.jdbc.Driver";
//    private static final String hostName = "dbc:mysql://localhost:3306/my_db?useSSL=false";
//    private static final String userName = "bestuser";
//    private static final String password = "bestuser";
//    public static Connection connection;
//    private static Util instance;
//    private static SessionFactory sessionFactory;
//
//
////    public static Connection getConnection() {
////        Connection connection = null;
////        try {
////            Class.forName(DB_DRIVER);
////            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
////            System.out.println("Connection OK");
////        } catch (ClassNotFoundException | SQLException e) {
////            e.printStackTrace();
////            System.out.println("Connection ERROR");
////        }
////        return connection;
////    }
//
//    public static Connection getConnection() {
//        try {
//            if (null == connection || connection.isClosed()) {
//                connection = DriverManager.getConnection(hostName, userName, password);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
////    public static Session getSession() {
////        if (sessionFactory == null) {
////            try {
////                Configuration configuration = new Configuration();
////                Properties settings = new Properties();
////                settings.put(Environment.DRIVER, DB_DRIVER);
////                settings.put(Environment.URL, DB_URL);
////                settings.put(Environment.USER, DB_USERNAME);
////                settings.put(Environment.PASS, DB_PASSWORD);
////                settings.put(Environment.DIALECT, DB_DIALECT);
////                settings.put(Environment.SHOW_SQL, "true");
////                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
////                configuration.setProperties(settings);
////                configuration.addAnnotatedClass(User.class);
////                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
////                        .applySettings(configuration.getProperties()).build();
////                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////        return sessionFactory.getCurrentSession();
////    }
//
//    public static Util getInstance() {
//        if (null == instance) {
//            instance = new Util();
//        }
//        return instance;
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            Properties properties = new Properties() {{
//                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//                setProperty("hibernate.connection.url", hostName);
//                setProperty("hibernate.connection.username", userName);
//                setProperty("hibernate.connection.password", password);
//                setProperty("hibernate.connection.driver_class", Driver);
//                setProperty("show_sql", "true");
//                setProperty("hibernate.hbm2ddl.auto", "create-drop");
//                setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
//            }};
//
//            sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
//        }
//        return sessionFactory;
//    }
//}
private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String hostName = "jdbc:mysql://localhost:3306/my_db?useSSL=false";
    private static final String userName = "bestuser";
    private static final String password = "bestuser";
    public static Connection connection;
    private static Util instance;
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties() {{
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.connection.url", hostName);
                setProperty("hibernate.connection.username", userName);
                setProperty("hibernate.connection.password", password);
                setProperty("hibernate.connection.driver_class", Driver);
                setProperty("show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
            }};

            sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            if (null == connection || connection.isClosed()) {
                connection = DriverManager.getConnection(hostName, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }
}