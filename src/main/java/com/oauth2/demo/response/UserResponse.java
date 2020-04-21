package com.oauth2.demo.response;

import com.oauth2.demo.enumuration.UserRole;

import java.time.LocalDateTime;

public interface UserResponse {
    Long getId();

    String getUsername();

    UserRole getRole();

    LocalDateTime getCreatedDate();
}
