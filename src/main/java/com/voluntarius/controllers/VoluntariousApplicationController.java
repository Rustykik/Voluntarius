package com.voluntarius.controllers;

import com.voluntarius.database.dao.EventDao;
import com.voluntarius.database.dao.UserDao;
import com.voluntarius.models.Event;
import com.voluntarius.models.User;
import com.voluntarius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class VoluntariousApplicationController {

    private final UserService userService;

    @GetMapping("users")
    public List<User> listUsers() throws SQLException {
        return userService.getUsers();
    }

    @GetMapping("events")
    public List<Event> listEvents() throws SQLException {
        return userService.getEvents();
    }

    @GetMapping("user/{id}")
    public User getUserId(@PathVariable Integer id) throws SQLException, IllegalAccessException {
        return userService.getUser(id);
    }

    //rework logic of getting user with id or without from where and etc Search for good practices
    //and patterns and exception non unique login and etc
    @PutMapping("profile/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User updatedUser) throws SQLException, IllegalAccessException {
        updatedUser.setId(id);
        userService.updateUser(updatedUser);
    }

    @PostMapping("user/{user_id}/subscribe/{event_id}")
    public void subscribeOnEvent(@PathVariable Integer user_id, @PathVariable Integer event_id) throws SQLException, IllegalAccessException {
        User user = userService.getUser(user_id);
        Event event = userService.getEvent(event_id);
        userService.subscribeToEvent(user, event);
    }
    @GetMapping("event/{id}")
    public Event getEventId(@PathVariable Integer id) throws SQLException, IllegalAccessException {
        return userService.getEvent(id);
    }

}
