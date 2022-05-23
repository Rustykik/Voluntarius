package com.voluntarius.service;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    boolean signIn (String login, String password); // sql exception
    boolean signUp (String firstname, String lastname, String login, String email, String password);
    public void updateUser(User user);
    void subscribeToEvent(Integer userId, Integer eventId) throws IllegalArgumentException;
    void unsubscribeFromEvent(Integer userId, Integer eventId) throws IllegalArgumentException;
    void setLikeToEvent(User user, Event event);
    void createEvent(String eventName,
                     String description,
                     LocalDateTime eventStart,
                     LocalDateTime eventEnd,
                     String location,
                     User owner);

    public User getUser(Integer id) throws IllegalArgumentException;
    public User getUser(String login) throws IllegalArgumentException;
    public List<User> getUsers();
    public Event getEvent(Integer id) throws IllegalArgumentException;
    public List<Event> getEvents();

}
