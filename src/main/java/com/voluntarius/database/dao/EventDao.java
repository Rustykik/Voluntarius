package com.voluntarius.database.dao;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventDao {
    public List<Event> getEvents();
    public int insertEvent(Event event);
    public int updateEvent(Event event);
    public Optional<Event> getEventById(Integer id);
    public List<Event> getEventsByEventName(String eventName);
    public List<Event> getSubscribedEvents(Integer ownerId);
    public List<Event> getEventsByLocation(String location);
    public List<Event> getCurrentEvents(LocalDateTime currentTime);
    public List<Event> getEventByOwnerId(Integer id);
    public List<Event> getEventUserRelatedEvents(User user);
}
