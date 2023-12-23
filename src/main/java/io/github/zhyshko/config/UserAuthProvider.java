package io.github.zhyshko.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.github.zhyshko.dto.user.UserData;
import com.auth0.jwt.JWT;

import java.util.*;

@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserData userData) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000L);
        return JWT.create()
                .withIssuer(userData.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("externalId", userData.getExternalId().toString())
                .withClaim("firstname", userData.getFirstName())
                .withClaim("lastname", userData.getLastName())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        UserData user = UserData.builder()
                .externalId(UUID.fromString(decodedJWT.getClaim("externalId").asString()))
                .email(decodedJWT.getIssuer())
                .firstName(decodedJWT.getClaim("firstname").asString())
                .lastName(decodedJWT.getClaim("lastname").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
