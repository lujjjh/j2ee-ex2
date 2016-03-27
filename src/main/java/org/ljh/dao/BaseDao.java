package org.ljh.dao;

import org.ljh.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {
    protected Connection conn;

    public BaseDao() throws SQLException, ClassNotFoundException {
        conn = Database.getConn();
    }
}
