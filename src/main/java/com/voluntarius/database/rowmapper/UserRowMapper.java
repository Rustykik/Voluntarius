package com.voluntarius.database.rowmapper;

import com.voluntarius.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return  new User(resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("login"),
                resultSet.getString("passwd"),
                resultSet.getString("email")
                );
    }
}
