package com.gamerconnect.gamerconnectapi.config.auth;

import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OAuthAuthentication implements Converter<Jwt, AbstractAuthenticationToken> {

    private final UserRepository userRepository;

    public OAuthAuthentication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var email = jwt.getClaims().get("email").toString();

        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            var authorities = user.get().getRoles().stream().map(role ->
                    new SimpleGrantedAuthority(role.getRoleName())).toList();
            return new JwtAuthenticationToken(jwt, authorities);
        }

        return new JwtAuthenticationToken(jwt, new ArrayList<>());
    }
}
