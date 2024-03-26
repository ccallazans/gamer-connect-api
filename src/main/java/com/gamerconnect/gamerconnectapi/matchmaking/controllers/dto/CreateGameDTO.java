package com.gamerconnect.gamerconnectapi.matchmaking.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CreateGameDTO(
        @NotNull
        @JsonProperty("title")
        String title,
        @NotNull
        @JsonProperty("publisher")
        String publisher,
        @NotNull
        @JsonProperty("release_year")
        Long releaseYear) {
}
