package com.gamerconnect.gamerconnectapi.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamerconnect.gamerconnectapi.entity.User;

import java.time.LocalDate;

public record UserDTO(
        @JsonProperty("email") String email,
        @JsonProperty("username") String username,
        @JsonProperty("created_at") LocalDate createdAt) {

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getEmail(),
                user.getUsername(),
                user.getCreatedAt().toLocalDate()
        );
    }
}
