package com.gamerconnect.gamerconnectapi.services;

import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final UserRepository userRepository;

    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DefaultOAuth2AuthenticatedPrincipal getContextUser() {
        return (DefaultOAuth2AuthenticatedPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
