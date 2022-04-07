package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.util.Set;

public interface UserDao {
    User getUserById (Integer id) throws SQLException;
    User getUserByLogin (String login) throws SQLException;
    void saveUser (User user) throws SQLException;
    Set<User> getUserSubscribedOnEvent (Event event) throws SQLException;
}
