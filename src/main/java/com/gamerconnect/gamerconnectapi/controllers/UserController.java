package com.gamerconnect.gamerconnectapi.controllers;

import com.gamerconnect.gamerconnectapi.controllers.dto.UserDTO;
import com.gamerconnect.gamerconnectapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<UserDTO> createUser() {
        var user = userService.createUser();
        return new ResponseEntity<>(UserDTO.from(user), HttpStatus.CREATED);
    }

    @GetMapping("/open")
    public String openEndpoint() {
        return "This is an open endpoint";
    }
}
