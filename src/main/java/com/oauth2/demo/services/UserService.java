package com.oauth2.demo.services;

import com.oauth2.demo.domain.User;
import com.oauth2.demo.payloads.UserPayload;

public interface UserService {

    /**
     * @param userPayload Request payload
     * @return Created office user
     */
    User create(UserPayload userPayload);
}
