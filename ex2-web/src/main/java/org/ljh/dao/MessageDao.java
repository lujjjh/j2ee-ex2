package org.ljh.dao;

import org.ljh.model.Message;
import org.ljh.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MessageDao extends BaseDao {
    public MessageDao() throws SQLException, ClassNotFoundException {
        super();
    }

    public List<Message> findAll() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select messages.id as id, user_id, username, " +
                "   title, content, created_at " +
                "from messages " +
                "join users on user_id = users.id " +
                "order by created_at desc limit 1000");
        List<Message> result = new LinkedList<>();

        while (rs.next()) {
            Message message = new Message();
            message.setId(rs.getInt("id"));
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            message.setUser(user);
            message.setTitle(rs.getString("title"));
            message.setContent(rs.getString("content"));
            message.setCreatedAt(rs.getDate("created_at"));
            result.add(message);
        }

        return result;
    }

    public boolean create(Message message) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("insert into messages (user_id, title, content) " +
                "values (?, ?, ?)");
        statement.setInt(1, message.getUser().getId());
        statement.setString(2, message.getTitle());
        statement.setString(3, message.getContent());
        return statement.executeUpdate() > 0;
    }
}
