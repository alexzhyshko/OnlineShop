package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.facade.AuthFacade;
import io.github.zhyshko.mapper.dto.user.UserMapper;
import io.github.zhyshko.model.user.User;
import io.github.zhyshko.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.UUID;

@Component
public class DefaultAuthFacade implements AuthFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserData login(UserData userData) {
        if(!userService.existsByEmail(userData.getEmail())) {
            throw new RuntimeException("User does not exist");
        }
        User existingUser = userService.getByEmail(userData.getEmail());
        if(!passwordEncoder.matches(CharBuffer.wrap(userData.getPassword()), existingUser.getPassword())) {
            throw new RuntimeException("Incorrect data input");
        }
        existingUser.setPassword(null);
        return userMapper.toDto(existingUser);
    }

    @Override
    public UserData register(UserData userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        if(userService.existsByEmail(userData.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        User user = userMapper.toModel(userData);
        user.setPassword(null);
        return userMapper.toDto(user);
    }
}
