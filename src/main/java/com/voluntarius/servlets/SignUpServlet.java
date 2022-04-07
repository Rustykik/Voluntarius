package com.voluntarius.servlets;

import com.voluntarius.database.dao.*;
import com.voluntarius.service.UserService;
import com.voluntarius.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignUpServlet", value = "/SignUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/SignUp.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl(SingletonDataAccess.getInstance().getSource());
        EventDao eventDao = new EventDaoImpl(SingletonDataAccess.getInstance().getSource());
        UserService userService = new UserServiceImpl(userDao, eventDao);
        try {
            if (userService.signUp(request.getParameter("firstname"),
                                    request.getParameter("lastname"),
                                    request.getParameter("email"),
                                    request.getParameter("login"),
                                    request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userDao.getUserByLogin(request.getParameter("login")));
                response.sendRedirect("/Profile");
            } else response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
