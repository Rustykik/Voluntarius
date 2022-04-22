package com.voluntarius.models;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String login;
    private String passwd;
    private String email;
    private List<Event> eventList;

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
        this.eventList = Collections.emptyList();
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
        this.eventList = Collections.emptyList();
    }

}
