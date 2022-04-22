package com.voluntarius.database.dao;

import com.voluntarius.database.rowmapper.UserRowMapper;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate source;

    // load only part of users 0-50 50-100 and etc
    @Override
    public List<User> getUsers() throws SQLException {
        return source.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public Optional<User> getUserById(Integer id) throws SQLException {
        String sql = "select * from users where id = ?";
        return source.query(sql, new UserRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws SQLException {
        String sql = "select * from users where login = ?";
        return source.query(sql, new UserRowMapper(), login).stream().findFirst();
    }

    @Override
    public int insertUser(User user) throws SQLException {
        String sql = "insert into users (firstname, lastname, login, passwd, email)" +
                "values (?, ?, ?, ?, ?)";
        return source.update(sql,
                            user.getFirstname(),
                            user.getLastname(),
                            user.getLogin(),
                            user.getPasswd(),
                            user.getEmail()
        );
    }

    @Override
    public void updateUser(User user) throws SQLException {
            String sql = "update users" +
                    "set firstname = ?, lastname = ?, login = ?, passwd = ?, email = ?" +
                    "where id = ?";
            source.update(sql,
                    user.getFirstname(),
                    user.getLastname(),
                    user.getLogin(),
                    user.getPasswd(),
                    user.getEmail(),
                    user.getId());
    }

    @Override
    public void updateSubscriptions(User user) throws SQLException {
       source.update("delete from subscribed where user_id = ?", user.getId());
       List<Event> events = user.getEventList();
       for (Event event : events)
           source.update("insert into subscribed (user_id, event_id)" +
                   "values (?, ?)", user.getId(), event.getId());
    }

    @Override
    public List<User> getUsersSubscribedOnEvent(Event event) throws SQLException {
        String sql = "select users.id, firstname, lastname, login, passwd, email from users" +
                "inner join subscribed on subscribed.user_id = users.id" +
                "inner join event_table on subscribed.event_id = event_table.id" +
                "where event_table.id = ?";
        return source.query(sql, new UserRowMapper(), event.getId());
    }
}
