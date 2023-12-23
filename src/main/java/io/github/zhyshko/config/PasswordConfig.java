package io.github.zhyshko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

}
