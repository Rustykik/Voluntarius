package com.voluntarius.servlets;

import com.voluntarius.database.dao.*;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import com.voluntarius.service.UserService;
import com.voluntarius.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "SignInServlet", value = "/SignIn")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        RequestDispatcher rd;
        if (user != null) {
            rd = request.getRequestDispatcher("/WEB-INF/jsp/Profile.jsp");
        } else {
            rd = request.getRequestDispatcher("/WEB-INF/jsp/SignIn.jsp");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl(SingletonDataAccess.getInstance().getSource());
        EventDao eventDao = new EventDaoImpl(SingletonDataAccess.getInstance().getSource());
        UserService userService = new UserServiceImpl(userDao, eventDao);
        try {
            if (userService.signIn(request.getParameter("login"), request.getParameter("password"))) {
                HttpSession session = request.getSession();
                User user = userDao.getUserByLogin(request.getParameter("login"));
                user.setEventSet(eventDao.getEventUserRelatedEvents(user));
                session.setAttribute("user", user);
                response.sendRedirect("/Profile");
            } else response.sendRedirect("/SignIn");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            System.out.println(e.getStackTrace());
            response.sendRedirect("");
        }
    }
}
