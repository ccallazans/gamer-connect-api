package com.gamerconnect.gamerconnectapi.services;

import com.gamerconnect.gamerconnectapi.entity.User;
import com.gamerconnect.gamerconnectapi.exceptions.BusinessException;
import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final UserRepository userRepository;

    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserFromContext() {
        var email = getEmailFromContext();
        var user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BusinessException("user not registered");
        }

        return user.get();
    }

    public String getEmailFromContext() {
        var authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var email = authentication
                .getTokenAttributes()
                .getOrDefault("email", null);

        if (email == null) {
            throw new BusinessException("invalid authentication context");
        }

        return email.toString();
    }
}
