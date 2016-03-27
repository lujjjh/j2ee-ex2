package org.ljh.servlet;

import com.mysql.jdbc.StringUtils;
import org.ljh.model.Message;
import org.ljh.model.User;
import org.ljh.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessagesCreateServlet extends HttpServlet {
    MessageService messageService;

    public MessagesCreateServlet() {
        messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/messages/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Map<String, String> messages = new HashMap<>();

        if (StringUtils.isNullOrEmpty(title)) {
            messages.put("title", "请输入标题");
        }
        if (StringUtils.isNullOrEmpty(content)) {
            messages.put("content", "请输入内容");
        }

        if (messages.isEmpty()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Message message = new Message();
            message.setUser(user);
            message.setTitle(title);
            message.setContent(content);
            if (messageService.create(message)) {
                response.sendRedirect("/");
                return;
            } else {
                messages.put("error", "留言失败");
            }
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/messages/create.jsp").forward(request, response);
    }
}
