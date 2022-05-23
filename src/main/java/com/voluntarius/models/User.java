package com.voluntarius.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String login;
    private String passwd;
    private String email;
    private List<Event> createdEvents;
    private List<Event> subscribedEvents;

    public User(String firstname,
                String lastname,
                String login,
                String password,
                String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.passwd = password;
        this.email = email;
        this.createdEvents = Collections.emptyList();
        this.subscribedEvents = Collections.emptyList();
    }

    public User(Integer id,
                String firstname,
                String lastname,
                String login,
                String password,
                String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.passwd = password;
        this.email = email;
        this.createdEvents = Collections.emptyList();
        this.subscribedEvents = Collections.emptyList();
    }

}
