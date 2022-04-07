package com.voluntarius.database.dao;

import com.voluntarius.database.db.SimpleJdbcTemplate;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class EventDaoImpl implements EventDao {
    private final SimpleJdbcTemplate source;

    public EventDaoImpl(SimpleJdbcTemplate source) {
        this.source = source;
    }

    private Event createEvent(ResultSet resultSet) throws SQLException {
        return new Event(resultSet.getInt("id"),
                        resultSet.getString("eventName"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("eventStart").toLocalDateTime(),
                        resultSet.getTimestamp("eventEnd").toLocalDateTime(),
                        resultSet.getString("location"),
                        resultSet.getInt("owner_id")
        );
    }

    @Override
    public void saveEvent(Event event) throws SQLException {
        source.prepareStatement("insert into event_table (owner_id, eventName, description, " +
                "eventStart, eventEnd, location, likes)" +
                "values (?, ?, ?, ?, ?, ?, ?)", stmt -> {
            stmt.setInt(1, event.getOwner_id());
            stmt.setString(2, event.getEventName());
            stmt.setString(3, event.getDescription());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(4, event.getEventStart().format(formatter));
            stmt.setString(5, event.getEventEnd().format(formatter));
            stmt.setString(6, event.getLocation());
            stmt.setInt(7, event.getLikes());
            stmt.execute();
        });

    }

    @Override
    public Event getEventById(Integer id) throws SQLException {

       return source.prepareStatement("select * from event_table where id = ?", stmt -> {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            return createEvent(resultSet);
        });
    }

    @Override
    public Event getEventByEventName(String eventName) throws SQLException {

        return source.prepareStatement("select * from event_table where eventName = ?", stmt -> {
            stmt.setString(1, eventName);
            ResultSet resultSet = stmt.executeQuery();
            return createEvent(resultSet);
        });
    }

    @Override
    public Set<Event> getSubscribedEvents(User owner) throws SQLException {

        return source.prepareStatement("select * from event_table " +
                "inner join subscribed on event_table.id = subscribed.event_id " +
                "inner join users on users.id = subscribed.user_id " +
                "where subscribed.user_id = ?", stmt-> {
            stmt.setInt(1, owner.getId());
            ResultSet resultSet = stmt.executeQuery();
            Set<Event> subEvents = new HashSet<>();
            while (resultSet.next()) {
                subEvents.add(createEvent(resultSet));
            }
            return subEvents;
        });
    }

    @Override
    public Set<Event> getEventsByLocation(String location) throws  SQLException {

        return source.prepareStatement("select * from event_table where location = ?", stmt -> {
            stmt.setString(1, location);
            ResultSet resultSet = stmt.executeQuery();
            Set<Event> eventSet = new HashSet<>();
            while (resultSet.next()) {
                eventSet.add(createEvent(resultSet));
            }
            return eventSet;
        });
    }

    @Override
    public Set<Event> getCurrentEvents(LocalDateTime currentTime) throws SQLException {

        return source.prepareStatement("select * from event_table where eventStart < ? and eventEnd > ?", stmt -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currTime = currentTime.format(formatter);
            stmt.setString(1, currTime);
            stmt.setString(2, currTime);
            ResultSet resultSet = stmt.executeQuery();
            Set<Event> currEvents = new HashSet<>();
            while (resultSet.next()) {
                currEvents.add(createEvent(resultSet));
            }
            return currEvents;
        });
    }

    @Override
    public Set<Event> getEventByOwnerId(Integer owner_id) throws SQLException {
        return source.prepareStatement("select * from event_table where owner_id = ?", stmt -> {
            stmt.setInt(1, owner_id);
            ResultSet resultSet = stmt.executeQuery();
            Set<Event> eventSet = new HashSet<>();
            while (resultSet.next()) {
                eventSet.add(createEvent(resultSet));
            }
            return eventSet;
        });
    }

    @Override
    public Set<Event> getEventUserRelatedEvents(User user) throws SQLException {
        return source.prepareStatement("select * " +
                "from event_table " +
                "where owner_id = ? " +
                "union " +
                "select event_table.id, owner_id, eventName, description, eventStart, eventEnd, location, likes " +
                "from event_table " +
                "inner join subscribed on event_table.id = subscribed.event_id " +
                "inner join users on subscribed.user_id = users.id " +
                "where users.id = ?", stmt -> {
            stmt.setInt(1, user.getId());
            stmt.setInt(2, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            Set<Event> eventSet = new HashSet<>();
            while (resultSet.next()) {
                eventSet.add(createEvent(resultSet));
            }
            return eventSet;
        });
    }
}
