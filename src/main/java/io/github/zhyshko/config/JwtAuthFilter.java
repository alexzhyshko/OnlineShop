package io.github.zhyshko.config;

import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header != null) {
            String[] authElements  = header.split(" ");

            if(authElements.length == 2 && "Bearer".equals(authElements[0])) {
                try{
                    Authentication authentication = userAuthProvider.validateToken(authElements[1]);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    UserData user = (UserData)authentication.getPrincipal();
                    request.getSession().setAttribute("userId", user.getExternalId());
                } catch(Exception e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
