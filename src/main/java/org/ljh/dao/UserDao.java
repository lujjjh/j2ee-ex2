package org.ljh.dao;

import org.ljh.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao {
    public UserDao() throws SQLException, ClassNotFoundException {
        super();
    }

    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = conn.prepareStatement("select * from users where username = ?");
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    public User authenticate(String username, String password) throws SQLException, ClassNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            return null;
        }
        String hashedPassword = user.getPassword();
        if (!BCrypt.checkpw(password, hashedPassword)) {
            return null;
        }
        return user;
    }

    public boolean create(String username, String password) throws SQLException, ClassNotFoundException {
        if (getUserByUsername(username) != null) {
            return false;
        }
        PreparedStatement statement = conn.prepareStatement("insert into users (username, password) values (?, ?)");
        statement.setString(1, username);
        statement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
        return statement.execute();
    }
}
