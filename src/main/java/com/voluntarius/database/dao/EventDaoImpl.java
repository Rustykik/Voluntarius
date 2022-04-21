package com.voluntarius.database.dao;

import com.voluntarius.database.rowmapper.EventRowMapper;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventDaoImpl implements EventDao {
    private final JdbcTemplate source;

    @Override
    public int insertEvent(Event event) throws SQLException {
        String sql = "INSERT " +
                "INTO event_table (owner_id, eventName, description, eventStart, eventEnd, location, likes)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return source.update(sql, event.getOwner_id(),
                event.getEventName(),
                event.getDescription(),
                event.getEventStart(),
                event.getEventEnd(),
                event.getLocation(),
                event.getLikes());
    }
    @Override
    public void updateEvent(Event event) throws SQLException {

    }

    @Override
    public Optional<Event> getEventById(Integer id) throws SQLException {
        String sql =
                "SELECT " +
                "* FROM event_table " +
                "WHERE id = ?";
        List<Event> query = source.query(sql, new EventRowMapper(), id);
        return query.stream().findFirst();
    }

    @Override
    public Optional<Event> getEventByEventName(String eventName) throws SQLException {
        String sql =
                "SELECT " +
                "* FROM event_table " +
                "WHERE eventName = ? = ?";
        return source.query(sql, new EventRowMapper(), eventName).stream().findFirst();
    }

    @Override
    public List<Event> getSubscribedEvents(User owner) throws SQLException {
       String sql = "select * from event_table " +
                "inner join subscribed on event_table.id = subscribed.event_id " +
                "inner join users on users.id = subscribed.user_id " +
                "where subscribed.user_id = ?";
       return source.query(sql, new EventRowMapper(), owner.getId());
    }

    @Override
    public List<Event> getEventsByLocation(String location) throws SQLException {
        String sql = "select * from event_table where location = ?";
        return source.query(sql, new EventRowMapper(), location);
    }

    @Override
    public List<Event> getCurrentEvents(LocalDateTime currentTime) throws SQLException {
        String sql = "select * from event_table where eventStart < ? and eventEnd > ?";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currTime = currentTime.format(formatter);
            return  source.query(sql, new EventRowMapper(), currTime, currTime);
    }

    @Override
    public List<Event> getEventByOwnerId(Integer id) throws SQLException {
        String sql = "select * from event_table where owner_id = ?";
        return source.query(sql, new EventRowMapper(), id);
    }

    @Override
    public List<Event> getEventUserRelatedEvents(User user) throws SQLException {
        String sql = "select * " +
                "from event_table " +
                "where owner_id = ? " +
                "union " +
                "select event_table.id, owner_id, eventName, description, eventStart, eventEnd, location, likes " +
                "from event_table " +
                "inner join subscribed on event_table.id = subscribed.event_id " +
                "inner join users on subscribed.user_id = users.id " +
                "where users.id = ?";
        return source.query(sql, new EventRowMapper(), user.getId());
    }
}
