package com.voluntarius.controllers;

import com.voluntarius.models.User;
import com.voluntarius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        try {
            return ResponseEntity.ok(service.getUsers());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getUser(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUser(@PathVariable String login) {
        try {
            return ResponseEntity.ok(service.getUser(login));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
//    TODO  rework signUp to consume User
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.signUp(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody User user) {
        try {
            return (service.signIn(user.getLogin(), user.getPasswd()) ?
            ResponseEntity.ok(HttpStatus.NO_CONTENT) :
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            service.updateUser(user);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/subscribe/{eventId}")
    public ResponseEntity subscribeToEvent(@PathVariable Integer id, @PathVariable Integer eventId) {
        try {
            service.subscribeToEvent(id, eventId);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/unsubscribe/{eventId}")
    public ResponseEntity unsubscribeFromEvent(@PathVariable Integer id, @PathVariable Integer eventId) {
        try {
            service.unsubscribeFromEvent(id, eventId);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
