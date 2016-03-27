package org.ljh.servlet;

import com.mysql.jdbc.StringUtils;
import org.ljh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private UserService userService;

    public RegisterServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        Map<String, String> messages = new HashMap<>();

        if (StringUtils.isNullOrEmpty(username)) {
            messages.put("username", "请输入用户名");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            messages.put("password", "请输入密码");
        }
        if (!password.equals(confirmPassword)) {
            messages.put("confirmPassword", "两次密码不一致");
        }

        if (messages.isEmpty()) {
            if (userService.find(username) != null) {
                messages.put("error", "用户名已存在");
            } else if (!userService.create(username, password)) {
                messages.put("error", "注册失败");
            } else {
                response.sendRedirect("/login");
                return;
            }
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
