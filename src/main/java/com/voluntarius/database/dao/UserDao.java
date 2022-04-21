package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getUserById (Integer id) throws SQLException;
    User getUserByLogin (String login) throws SQLException;
    void saveUser (User user) throws SQLException;
    List<User> getUserSubscribedOnEvent (Event event) throws SQLException;
}
