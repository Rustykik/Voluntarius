package com.voluntarius.service;

import com.voluntarius.database.dao.EventDao;
import com.voluntarius.database.dao.UserDao;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final EventDao eventDao;

    @Override
    public boolean signIn(String login, String password) {

        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isEmpty() || !user.get().getPasswd().equals(password)) {
            return false;
        }
        return true;
    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    // maybe switch case (by id or login) to evoid code duplication
    @Override
    public User getUser(Integer id) throws IllegalArgumentException {
        User user =  userDao.getUserById(id).orElseThrow(()-> new IllegalArgumentException("User not found"));
        user.setCreatedEvents(eventDao.getEventByOwnerId(user.getId()));
        user.setSubscribedEvents(eventDao.getSubscribedEvents(user.getId()));
        return user;
    }

    @Override
    public User getUser(String login) throws IllegalArgumentException {
        User user =  userDao.getUserByLogin(login).orElseThrow(()-> new IllegalArgumentException("User not found"));
        user.setCreatedEvents(eventDao.getEventByOwnerId(user.getId()));
        user.setSubscribedEvents(eventDao.getSubscribedEvents(user.getId()));
        return user;
    }

    @Override
    public List<User> getUsers() {

        List<User> users = userDao.getUsers();

        for (User user: users) {
            user.setCreatedEvents(eventDao.getEventByOwnerId(user.getId()));
            user.setSubscribedEvents(eventDao.getSubscribedEvents(user.getId()));
        }
        return users;
    }

    @Override
    public Event getEvent(Integer id) throws IllegalArgumentException {
        return eventDao.getEventById(id).orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }

    @Override
    public List<Event> getEvents() {
        return eventDao.getEvents();
    }

    @Override
    public boolean signUp(String firstname, String lastname, String login, String email, String password) {

        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isPresent()) // so we need it? because it unique in db
        {
            return false;
        }
        userDao.insertUser(new User(firstname, lastname, login, password, email));
        return true;
    }

    // subscribe and set like should get ids of users and events
    @Override
    public void subscribeToEvent(Integer userId, Integer eventId) throws IllegalArgumentException {
        User user = getUser(userId);
        Event event = eventDao.getEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Did not found event with id" + eventId));
        List<Event> subscribedEvents = new ArrayList<Event>(user.getSubscribedEvents());
        if (subscribedEvents.contains(event)) {
            return;
        }
        // TODO fix shit code
        subscribedEvents.add(event);
        user.setSubscribedEvents(subscribedEvents);
        userDao.updateSubscriptions(user);
    }

    @Override
    public void unsubscribeFromEvent(Integer userId, Integer eventId) throws IllegalArgumentException {
        User user = getUser(userId);
        Event event = eventDao.getEventById(eventId).orElseThrow(() -> new IllegalArgumentException("Did not found event with id" + eventId));
        List<Event> subscribedEvents = new ArrayList<Event>(user.getSubscribedEvents());
        if (subscribedEvents.contains(event) == false) {
            return;
        }
        subscribedEvents.remove(event);
        user.setSubscribedEvents(subscribedEvents);
        userDao.updateSubscriptions(user);
    }

    @Override
    public void setLikeToEvent(User user, Event event) {
        Integer Likes = event.getLikes();
        Likes += 1;
    }

    @Override
    public void createEvent(String eventName,
                            String description,
                            LocalDateTime eventStart,
                            LocalDateTime eventEnd,
                            String location,
                            User owner) {
        Event event = new Event(eventName,
                                description,
                                eventStart,
                                eventEnd,
                                location,
                                owner.getId());
        eventDao.insertEvent(event);
    }
}
