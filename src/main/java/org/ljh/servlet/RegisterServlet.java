package org.ljh.servlet;

import com.mysql.jdbc.StringUtils;
import org.ljh.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password) ||
                !password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp");
            return;
        }
        try {
            UserDao userDao = new UserDao();
            userDao.create(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp");
    }
}
