package com.company.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

    private static DBConnectionProvider provider;
    private String dbDriver="com.mysql.cj.jdbc.Driver";
    private String dbUrl="jdbc:mysql://localhost:3306/bdgdb";
    private String dbUsername="root";
    private String dbPassword="1234";

    private Connection connection;

    private DBConnectionProvider() {
        try {
            Class.forName(dbDriver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to " +
                    "initialize DB Connection Provider", e);
        }
    }



    public static DBConnectionProvider getInstance() {
        if (provider == null) {
            provider = new DBConnectionProvider();
        }
        return provider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl,
                        dbUsername, dbPassword);

            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
