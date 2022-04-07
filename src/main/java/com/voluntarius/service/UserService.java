package com.voluntarius.service;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface UserService {
    boolean signIn (String login, String password) throws SQLException; // sql exception
    boolean signUp (String firstname, String lastname, String login, String email, String password) throws SQLException;
    void subscribeToEvent(User user, Event event);
    void setLikeToEvent(User user, Event event);
    void createEvent(String eventName,
                     String description,
                     LocalDateTime eventStart,
                     LocalDateTime eventEnd,
                     String location,
                     User owner);
}
