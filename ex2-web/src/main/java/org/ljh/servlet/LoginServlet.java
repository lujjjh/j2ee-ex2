package org.ljh.servlet;

import com.mysql.jdbc.StringUtils;
import org.ljh.model.User;
import org.ljh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (StringUtils.isNullOrEmpty(username)) {
            messages.put("username", "请输入用户名");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            messages.put("password", "请输入密码");
        }

        if (messages.isEmpty()) {
            User user = userService.find(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/");
                return;
            } else {
                messages.put("error", "用户名和密码不匹配");
            }
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
