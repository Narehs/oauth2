package com.oauth2.demo.controller;

import com.oauth2.demo.payloads.UserPayload;
import com.oauth2.demo.response.UserResponse;
import com.oauth2.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ProjectionFactory projectionFactory;
    private final MessageSource messageSource;

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserPayload payload) {
        return projectionFactory.createProjection(UserResponse.class, userService.create(payload));
    }
}
