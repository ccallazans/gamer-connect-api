package com.gamerconnect.gamerconnectapi.account.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CreateUserDTO(
        @NotNull
        @JsonProperty("username")
        String username,

        @NotNull
        @Email
        @JsonProperty("email")
        String email) {
}
