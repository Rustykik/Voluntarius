package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> getUserById (Integer id) throws SQLException;
    Optional<User> getUserByLogin (String login) throws SQLException;
    int insertUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    List<User> getUsersSubscribedOnEvent (Event event) throws SQLException;
}
