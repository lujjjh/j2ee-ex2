package org.ljh.servlet;

import org.ljh.model.Message;
import org.ljh.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MessagesServlet extends HttpServlet {
    MessageService messageService;

    public MessagesServlet() {
        messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Message> messages = messageService.findAll();
        if (messages != null) {
            request.setAttribute("messages", messages);
        }
        request.getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
    }
}
