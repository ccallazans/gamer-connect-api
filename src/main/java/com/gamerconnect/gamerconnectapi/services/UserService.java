package com.gamerconnect.gamerconnectapi.services;

import com.gamerconnect.gamerconnectapi.entity.User;
import com.gamerconnect.gamerconnectapi.exceptions.BusinessException;
import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final AuthUserService authUserService;
    private final UserRepository userRepository;

    public UserService(AuthUserService authUserService, UserRepository userRepository) {
        this.authUserService = authUserService;
        this.userRepository = userRepository;
    }

    public User createUser() {

        var contextUser = authUserService.getContextUser();
        var email = (String) contextUser.getAttribute("email");

        validateEmail(email);

        var user = User.builder()
                .id(UUID.randomUUID())
                .username(email)
                .email(email)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return userRepository.save(user);
    }

    private void validateEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new BusinessException(String.format("invalid email format: %s", email));
        }

        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(String.format("email already exists: %s", email));
        }
    }
}
