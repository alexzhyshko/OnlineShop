package io.github.zhyshko.service.user;

import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User save(User user);

    User createUser(User user);

    boolean existsByEmail(String email);

    User getByEmail(String email);

    boolean matchPasswords(UserData userData);

    User getByExternalId(UUID externalId);

    Optional<UUID> getCurrentUserExternalId();
}
