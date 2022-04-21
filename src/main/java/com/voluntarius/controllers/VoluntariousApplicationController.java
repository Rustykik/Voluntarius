package com.voluntarius.controllers;

import com.voluntarius.database.dao.EventDao;
import com.voluntarius.database.dao.UserDao;
import com.voluntarius.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class VoluntariousApplicationController {

    private final UserService userService;
}
