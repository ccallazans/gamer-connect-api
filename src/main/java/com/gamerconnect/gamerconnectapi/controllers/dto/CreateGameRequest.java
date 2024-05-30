package com.gamerconnect.gamerconnectapi.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGameRequest(
        @JsonProperty("title") @NotBlank String title,
        @JsonProperty("publisher") @NotBlank String publisher,
        @JsonProperty("release_year") @NotNull Long releaseYear) {
}
