package org.ljh.service;

import org.ljh.dao.MessageDao;
import org.ljh.model.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageService {
    private MessageDao messageDao = null;

    public MessageService() {
        try {
            messageDao = new MessageDao();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Message> findAll() {
        if (messageDao == null) {
            return null;
        }
        try {
            return messageDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(Message message) {
        try {
            return messageDao.create(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
