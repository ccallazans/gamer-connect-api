package com.gamerconnect.gamerconnectapi.account.services;

import com.gamerconnect.gamerconnectapi.account.entity.User;
import com.gamerconnect.gamerconnectapi.account.repository.UserRepository;
import com.gamerconnect.gamerconnectapi.exceptions.BusinessException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String username) {

        validateUsername(username);
        validateEmail(email);

        var user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .email(email)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return userRepository.save(user);
    }

    private void validateUsername(String username) {
        if (username.length() < 5 || username.length() > 15) {
            throw new BusinessException(String.format("invalid username size: %s", username));
        }

        if(userRepository.existsByUsername(username)) {
            throw new BusinessException(String.format("username already exists: %s", username));
        }
    }

    private void validateEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new BusinessException(String.format("invalid email format: %s", email));
        }

        if(userRepository.existsByEmail(email)) {
            throw new BusinessException(String.format("username already exists: %s", email));
        }
    }
}
