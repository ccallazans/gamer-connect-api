package com.gamerconnect.gamerconnectapi.config.auth;

import com.gamerconnect.gamerconnectapi.exceptions.AuthenticationException;
import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public final class GoogleTokenIntrospector implements OpaqueTokenIntrospector {

    private static final NetHttpTransport transport = new NetHttpTransport();
    private static final GsonFactory jsonFactory = new GsonFactory();

    private final UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    public GoogleTokenIntrospector(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {

        var payload = getPayload(token);
        var email = payload.getEmail();
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return new DefaultOAuth2AuthenticatedPrincipal(
                    Map.of("email", email),
                    List.of(new SimpleGrantedAuthority("GUEST")));
        }

        return new DefaultOAuth2AuthenticatedPrincipal(
                Map.of("email", email),
                List.of(new SimpleGrantedAuthority("USER")));
    }

    private GoogleIdToken.Payload getPayload(String idToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            GoogleIdToken googleToken = verifier.verify(idToken);

            if (Objects.isNull(googleToken)) {
                throw new AuthenticationException("Invalid token");
            }
            return googleToken.getPayload();

        } catch (GeneralSecurityException | IOException e) {
            throw new AuthenticationException("Error when validating token");
        }
    }
}