package com.gamerconnect.gamerconnectapi.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamerconnect.gamerconnectapi.entity.Game;

public record GameResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("publisher") String publisher,
        @JsonProperty("release_year") Long releaseYear) {

    public static GameResponse from(Game game) {
        return new GameResponse(
                game.getId(),
                game.getTitle(),
                game.getPublisher(),
                game.getReleaseYear()
        );
    }
}
