package com.voluntarius.models;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private Integer id;
    private String eventName;
    private String description;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private String location;
    private Integer owner_id;

    private Integer likes;
    private Integer subscribed;

    public Event(Integer id,
                 String eventName,
                 String description,
                 LocalDateTime eventStart,
                 LocalDateTime eventEnd,
                 String location,
                 Integer owner) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.location = location;
        this.owner_id = owner;
        this.likes = 0;
        this.subscribed = 0;
    }
    public Event(
                 String eventName,
                 String description,
                 LocalDateTime eventStart,
                 LocalDateTime eventEnd,
                 String location,
                 Integer owner) {
        this.eventName = eventName;
        this.description = description;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.location = location;
        this.owner_id = owner;
        this.likes = 0;
        this.subscribed = 0;
    }
}
