package com.gamerconnect.gamerconnectapi.config;

import com.gamerconnect.gamerconnectapi.config.auth.GoogleTokenIntrospector;
import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST, "/users/").hasAuthority("GUEST")
                        .anyRequest().hasAuthority("USER")
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        .opaqueToken(opaqueToken -> opaqueToken.introspector(introspector()))
                ).sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public OpaqueTokenIntrospector introspector() {
        return new GoogleTokenIntrospector(userRepository);
    }
}