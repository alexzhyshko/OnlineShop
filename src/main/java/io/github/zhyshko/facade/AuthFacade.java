package io.github.zhyshko.facade;

import io.github.zhyshko.dto.user.UserData;

public interface AuthFacade {

    UserData login(UserData userData);

    UserData register(UserData userData);
}
