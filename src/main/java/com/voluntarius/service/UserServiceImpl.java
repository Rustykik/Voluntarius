package com.voluntarius.service;

import com.voluntarius.database.dao.EventDao;
import com.voluntarius.database.dao.UserDao;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

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
    public boolean signUp(String firstname, String lastname, String login, String email, String password) throws SQLException {

        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isEmpty()) // so we need it? because it unique in db
        {
            userDao.insertUser(new User(firstname, lastname, login, email, password);
            return true;
        }
        return false;
    }

    @Override
    public void subscribeToEvent(User user, Event event) {
        Set<Event> userEvents = user.getEventSet();
        userEvents.add(event);
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
        subscribeToEvent(owner, event);
    }
}
