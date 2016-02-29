/*
 * Copyright (C) 2015 Lev Tien
 * 1002 B1 building - HUST , Ta Quang Buu, HN
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Lev Tien
 */
public class DBConnectHelper {

    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gr2?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    // init connection object
    private static Connection connection;
    // init properties object
    private static Properties properties;

    public DBConnectHelper() {
    }

    public static Connection getDBConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    // create properties
    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    private static void connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Connect Failed");
            }
        }
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Disconnect Failed");
            }
        }
        System.out.println("Disconnect success");
    }

}
