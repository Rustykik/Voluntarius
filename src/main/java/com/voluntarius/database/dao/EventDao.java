package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

public interface EventDao {
    public void saveEvent(Event event) throws SQLException;
    public Event getEventById(Integer id) throws SQLException;
    public Event getEventByEventName(String eventName) throws SQLException;
    public Set<Event> getSubscribedEvents(User owner) throws SQLException;
    public Set<Event> getEventsByLocation(String location) throws SQLException;
    public Set<Event> getCurrentEvents(LocalDateTime currentTime) throws SQLException;
    public Set<Event> getEventByOwnerId(Integer id) throws SQLException;
    public Set<Event> getEventUserRelatedEvents(User user) throws SQLException;
}
