package com.voluntarius.database.dao;

import com.voluntarius.database.rowmapper.EventRowMapper;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventDaoImpl implements EventDao {
    private final JdbcTemplate source;

    @Override
    public List<Event> getEvents() {
        return source.query("SELECT * FROM event_table", new EventRowMapper());
    }

    @Override
    public int insertEvent(Event event) {
        String sql = "INSERT " +
                "INTO event_table (owner_id, eventName, description, eventStart, eventEnd, location, likes)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return source.update(sql, event.getOwnerId(),
                event.getEventName(),
                event.getDescription(),
                event.getEventStart(),
                event.getEventEnd(),
                event.getLocation(),
                event.getLikes());
    }
    @Override
    public int updateEvent(Event event) {
        String sql = "UPDATE event_table " +
                "SET eventName = ?, description = ?, eventStart = ?, eventEnd = ?, location = ?, likes = ? " +
                "where id = ?";
        return source.update(sql,
                event.getEventName(),
                event.getDescription(),
                event.getEventStart(),
                event.getEventEnd(),
                event.getLocation(),
                event.getLikes(),
                event.getId());
    }

    @Override
    public Optional<Event> getEventById(Integer id) {
        String sql =
                "SELECT " +
                "* FROM event_table " +
                "WHERE id = ?";
        List<Event> query = source.query(sql, new EventRowMapper(), id);
        return query.stream().findFirst();
    }

    @Override
    public List<Event> getEventsByEventName(String eventName) {
        String sql =
                "SELECT " +
                "* FROM event_table " +
                "WHERE eventName = ?";
        return source.query(sql, new EventRowMapper(), eventName);
    }

    @Override
    public List<Event> getSubscribedEvents(Integer ownerId) {
       String sql = "select * from event_table " +
                "inner join subscribed on event_table.id = subscribed.event_id " +
                "inner join users_table on users_table.id = subscribed.user_id " +
                "where subscribed.user_id = ?";
       return source.query(sql, new EventRowMapper(), ownerId);
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        String sql = "select * from event_table where location = ?";
        return source.query(sql, new EventRowMapper(), location);
    }

    @Override
    public List<Event> getCurrentEvents(LocalDateTime currentTime) {
        String sql = "select * from event_table where eventStart < ? and eventEnd > ?";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currTime = currentTime.format(formatter);
            return  source.query(sql, new EventRowMapper(), currTime, currTime);
    }

    @Override
    public List<Event> getEventByOwnerId(Integer id) {
        String sql = "select * from event_table where owner_id = ?";
        return source.query(sql, new EventRowMapper(), id);
    }

    @Override
    public List<Event> getEventUserRelatedEvents(User user) {
        String sql = "select * " +
                "from event_table " +
                "where owner_id = ? " +
                "union " +
                "select event_table.id, owner_id, eventName, description, eventStart, eventEnd, location, likes " +
                "from event_table " +
                "inner join subscribed on event_table.id = subscribed.event_id " +
                "inner join users_table on subscribed.user_id = users_table.id " +
                "where users_table.id = ?";
        return source.query(sql, new EventRowMapper(), user.getId());
    }
}
