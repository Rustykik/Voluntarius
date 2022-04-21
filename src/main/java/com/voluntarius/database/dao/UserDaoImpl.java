package com.voluntarius.database.dao;

import com.voluntarius.database.db.SimpleJdbcTemplate;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate source;

    @Override
    public User getUserById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return null;
    }

    @Override
    public void saveUser(User user) throws SQLException {

    }

    @Override
    public List<User> getUserSubscribedOnEvent(Event event) throws SQLException {
        return null;
    }
//    private User CreateUser(ResultSet resultSet) throws SQLException {
//        return  new User(
//                resultSet.getInt("id"),
//                resultSet.getString("firstname"),
//                resultSet.getString("lastname"),
//                resultSet.getString("login"),
//                resultSet.getString("passwd"),
//                resultSet.getString("email")
//                );
//    }
//
//    @Override
//    public User getUserById(Integer id) throws SQLException {
//        return source.prepareStatement("select * from users where id = ?", stmt -> {
//            stmt.setInt(1, id);
//            ResultSet resultSet = stmt.executeQuery();
//            return CreateUser(resultSet);
//        });
//    }
//
//    @Override
//    public User getUserByLogin(String login) throws SQLException {
//        return source.prepareStatement("select * from users where login = ?", stmt -> {
//            stmt.setString(1, login);
//            ResultSet resultSet = stmt.executeQuery();
//            if (resultSet.next())
//                return CreateUser(resultSet);
//            else
//                return null;
//        });
//    }

//    @Override
//    public void saveUser(User user) throws SQLException {
//        source.prepareStatement("insert into users (firstname, lastname, login, passwd, email)" +
//                "values (?, ?, ?, ?, ?)", stmt -> {
//            stmt.setString(1, user.getFirstname());
//            stmt.setString(1, user.getLastname());
//            stmt.setString(1, user.getLogin());
//            stmt.setString(1, user.getPasswd());
//            stmt.setString(1, user.getEmail());
//        });
//    }
//
//    @Override
//    public Set<User> getUserSubscribedOnEvent(Event event) throws SQLException {
//
//        return source.prepareStatement("select users.id, firstname, lastname, login, passwd, email from users" +
//                "inner join subscribed on subscribed.user_id = users.id" +
//                "inner join event_table on subscribed.event_id = event_table.id" +
//                "where event_table.id = ?", stmt -> {
//            stmt.setInt(1, event.getId());
//            ResultSet resultSet = stmt.executeQuery();
//            Set<User> subscribedUsers = new HashSet<>();
//            while (resultSet.next()) {
//                subscribedUsers.add(CreateUser(resultSet));
//            }
//            return subscribedUsers;
//        });
//    }
}
