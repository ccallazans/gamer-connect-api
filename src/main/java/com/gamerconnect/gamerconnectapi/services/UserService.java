package com.gamerconnect.gamerconnectapi.services;

import com.gamerconnect.gamerconnectapi.config.auth.RoleEnum;
import com.gamerconnect.gamerconnectapi.entity.Role;
import com.gamerconnect.gamerconnectapi.entity.User;
import com.gamerconnect.gamerconnectapi.exceptions.BusinessException;
import com.gamerconnect.gamerconnectapi.repository.RoleRepository;
import com.gamerconnect.gamerconnectapi.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final AuthUserService authUserService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(AuthUserService authUserService, UserRepository userRepository, RoleRepository roleRepository) {
        this.authUserService = authUserService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser() {
        var email =  authUserService.getEmailFromContext();

        var emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(email)) {
            throw new BusinessException("invalid email");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BusinessException("email already registered!");
        }

        var role = roleRepository.findByRoleName(RoleEnum.USER.name());
        var user = User.builder()
                .email(email)
                .username(email)
                .createdAt(LocalDateTime.now())
                .roles(Set.of(role))
                .build();

        return userRepository.save(user);
    }
}
