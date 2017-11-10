package services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlManager {
    private static final MysqlManager mysqlManagerInstance = new MysqlManager();
    private String USERNAME;
    private String PASSWORD;
    private String URL;
    private String DRIVER_CLASS_NAME;
    private MysqlManager() {
        Properties properties = new Properties();
        InputStream is = MysqlManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER_CLASS_NAME = properties.getProperty("jdbc.driverClassName");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");
        URL = properties.getProperty("jdbc.url");

    }

    public static MysqlManager getInstance(){
        return mysqlManagerInstance;
    }
    public Connection getConnection(String name) throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS_NAME);
        return DriverManager.getConnection(URL+name,USERNAME,PASSWORD);


    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS_NAME);
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);


    }

}
