package fr.coco.bungeeban.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by coco33910 on 22/02/2016.
 * SqlUtils
 */
public class SqlUtils {

    private String host, username, password, dbName, tableName;
    private Connection connection;

    public SqlUtils(String host, String username, String password, String dbName, String tableName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.tableName = tableName;
    }


    public void connection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbName, username, password);
            System.out.println("The connection to the database is done !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            if (connection == null || connection.isValid(5) || connection.isClosed()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Connection getConnection() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }

    public String getTableName() {
        return tableName;
    }

}
