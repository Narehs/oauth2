package com.oauth2.demo.services;

import com.oauth2.demo.domain.User;
import com.oauth2.demo.enumuration.UserRole;
import com.oauth2.demo.payloads.UserPayload;
import com.oauth2.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;

    @Override
    @Transactional
    public User create(UserPayload payload) {
        final User user = new User();
        BeanUtils.copyProperties(payload, user);
        user.setLocked(false);
        user.setEnabled(true);
        user.setRole(UserRole.ROLE_USER);
        user.setPassword(passwordEncode.encode(payload.getPassword()));
        return userRepository.save(user);
    }
}
