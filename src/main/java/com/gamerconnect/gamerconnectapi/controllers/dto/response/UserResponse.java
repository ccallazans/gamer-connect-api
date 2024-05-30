package com.gamerconnect.gamerconnectapi.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamerconnect.gamerconnectapi.entity.User;

import java.time.LocalDate;

public record UserResponse(
        @JsonProperty("email") String email,
        @JsonProperty("username") String username,
        @JsonProperty("created_at") LocalDate createdAt) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getUsername(),
                user.getCreatedAt().toLocalDate()
        );
    }
}
