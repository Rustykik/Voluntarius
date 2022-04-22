package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventDao {
    public List<Event> getEvents() throws SQLException;
    public int insertEvent(Event event) throws SQLException;
    public void updateEvent(Event event) throws SQLException;
    public Optional<Event> getEventById(Integer id) throws SQLException;
    public Optional<Event> getEventByEventName(String eventName) throws SQLException;
    public List<Event> getSubscribedEvents(User owner) throws SQLException;
    public List<Event> getEventsByLocation(String location) throws SQLException;
    public List<Event> getCurrentEvents(LocalDateTime currentTime) throws SQLException;
    public List<Event> getEventByOwnerId(Integer id) throws SQLException;
    public List<Event> getEventUserRelatedEvents(User user) throws SQLException;
}
