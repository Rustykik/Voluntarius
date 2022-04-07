package com.voluntarius.servlets;

import com.voluntarius.database.dao.*;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import com.voluntarius.service.UserService;
import com.voluntarius.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ProfileServlet", value = "/Profile")
public class ProfileServlet extends HttpServlet {
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
        EventDao eventDao = new EventDaoImpl(SingletonDataAccess.getInstance().getSource());
        User user = (User) request.getSession().getAttribute("user");
        RequestDispatcher rd;
        Event event = new Event(request.getParameter("eventName"),
                                request.getParameter("description"),
                                LocalDateTime.parse(request.getParameter("eventStart")),
                                LocalDateTime.parse(request.getParameter("eventEnd")),
                                request.getParameter("location"),
                                user.getId());
        try {
            System.out.println(event.toString());
            user.eventSet.add(event);
            eventDao.saveEvent(event);
            response.sendRedirect("/Profile");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            System.out.println(e.getStackTrace());
            response.sendRedirect("/Profile");
        }
    }
}
