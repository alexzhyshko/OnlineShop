package io.github.zhyshko.controller;

import io.github.zhyshko.config.UserAuthProvider;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.facade.AuthFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthFacade authFacade;
    @Autowired
    private UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    @ResponseBody
    public UserData login(@RequestBody UserData userData) {
        UserData user = authFacade.login(userData);
        user.setToken(userAuthProvider.createToken(user));
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public UserData register(@RequestBody UserData userData) {
        UserData user = authFacade.register(userData);
        user.setToken(userAuthProvider.createToken(user));
        return user;
    }

}
