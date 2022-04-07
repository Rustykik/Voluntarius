package com.voluntarius.models;

import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String login;
    private String passwd;
    private String email;
    public Set<Event> eventSet;

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
        this.eventSet = Collections.emptySet();
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
        this.eventSet = Collections.emptySet();
    }

}
