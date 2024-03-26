package com.gamerconnect.gamerconnectapi.account.controllers;

import com.gamerconnect.gamerconnectapi.account.services.UserService;
import com.gamerconnect.gamerconnectapi.account.controllers.dto.CreateUserDTO;
import com.gamerconnect.gamerconnectapi.account.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {

        var user = userService.createUser(createUserDTO.email(), createUserDTO.username());

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
