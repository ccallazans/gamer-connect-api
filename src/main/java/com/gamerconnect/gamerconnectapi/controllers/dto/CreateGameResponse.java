package com.gamerconnect.gamerconnectapi.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamerconnect.gamerconnectapi.entity.Game;

public record CreateGameResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("publisher") String publisher,
        @JsonProperty("release_year") Long releaseYear) {

    public static CreateGameResponse from(Game game) {
        return new CreateGameResponse(
                game.getId(),
                game.getTitle(),
                game.getPublisher(),
                game.getReleaseYear()
        );
    }
}
