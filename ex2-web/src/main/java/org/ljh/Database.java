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
            String url = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true&" +
                    "useUnicode=true&characterEncoding=utf8", host, port, name);
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
}
