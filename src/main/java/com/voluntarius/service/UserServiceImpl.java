package com.voluntarius.service;

import com.voluntarius.database.dao.EventDao;
import com.voluntarius.database.dao.UserDao;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final EventDao eventDao;

    @Override
    public boolean signIn(String login, String password) throws  SQLException {

        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isPresent()) {
            if (user.get().getPasswd().equals(password))
                return true;
        }
        return false;
    }
    @Override
    public void updateUser(User user) throws SQLException {
        userDao.updateUser(user);
    }
    // maybe switch case (by id or login)
    @Override
    public User getUser(Integer id) throws SQLException, IllegalAccessException {
        User user =  userDao.getUserById(id).orElseThrow(()-> new IllegalAccessException("User not found"));
        return user;
    }
    @Override
    public List<User> getUsers() throws SQLException {
        return userDao.getUsers();
    }

    @Override
    public Event getEvent(Integer id) throws SQLException, IllegalAccessException {
        return eventDao.getEventById(id).orElseThrow(() -> new IllegalAccessException("Event not found"));
    }

    @Override
    public List<Event> getEvents() throws SQLException {
        return eventDao.getEvents();
    }




    @Override
    public boolean signUp(String firstname, String lastname, String login, String email, String password) throws SQLException {

        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isEmpty()) // so we need it? because it unique in db
        {
            userDao.insertUser(new User(firstname, lastname, login, email, password));
            return true;
        }
        return false;
    }

    @Override
    public void subscribeToEvent(User user, Event event) throws SQLException {
        List<Event> userEvents = user.getEventList();
        userEvents.add(event);
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
                            User owner)  throws SQLException {
        Event event = new Event(eventName,
                                description,
                                eventStart,
                                eventEnd,
                                location,
                                owner.getId());
        eventDao.insertEvent(event);
    }
}
