package com.voluntarius.service;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    boolean signIn (String login, String password) throws SQLException; // sql exception
    boolean signUp (String firstname, String lastname, String login, String email, String password) throws SQLException;
    public void updateUser(User user) throws SQLException;
    void subscribeToEvent(User user, Event event) throws SQLException;
    void setLikeToEvent(User user, Event event);
    void createEvent(String eventName,
                     String description,
                     LocalDateTime eventStart,
                     LocalDateTime eventEnd,
                     String location,
                     User owner) throws SQLException;

    public User getUser(Integer id) throws SQLException, IllegalAccessException;
    public List<User> getUsers() throws SQLException;

    public Event getEvent(Integer id) throws SQLException, IllegalAccessException;
    public List<Event> getEvents() throws SQLException;

}
