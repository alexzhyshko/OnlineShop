package io.github.zhyshko.service.user.impl;

import io.github.zhyshko.dao.user.UserDao;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.model.user.User;
import io.github.zhyshko.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        user.setExternalId(UUID.randomUUID());
        return userDao.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }


    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found for such email"));
    }

    @Override
    public boolean matchPasswords(UserData userData) {
        byte[] inputPwd = userData.getPassword().getBytes();

        byte[] existingPwd = getByEmail(userData.getEmail()).getPassword().getBytes();

        return Arrays.equals(inputPwd, existingPwd);
    }

    @Override
    public User getByExternalId(UUID externalId) {
        return userDao.findByExternalId(externalId).orElseThrow(() ->  new RuntimeException("User not found for such id"));
    }
}
