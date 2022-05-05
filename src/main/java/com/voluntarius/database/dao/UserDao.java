package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getUsers();
    Optional<User> getUserById (Integer id);
    Optional<User> getUserByLogin (String login);
    int insertUser(User user);
    int updateUser(User user);
    void updateSubscriptions(User user);
    List<User> getUsersSubscribedOnEvent (Event event);
}
