package org.ljh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn = null;

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            String host = System.getenv("DB_HOST");
            String port = System.getenv("DB_PORT");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            String name = System.getenv("DB_NAME");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, user, password);
            conn.createStatement().execute("create database if not exists " + name);
            conn.setCatalog(name);
        }
        return conn;
    }
}
