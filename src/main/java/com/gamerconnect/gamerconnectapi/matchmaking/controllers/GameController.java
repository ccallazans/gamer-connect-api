package com.gamerconnect.gamerconnectapi.matchmaking.controllers;

import com.gamerconnect.gamerconnectapi.matchmaking.controllers.dto.CreateGameDTO;
import com.gamerconnect.gamerconnectapi.matchmaking.entity.Game;
import com.gamerconnect.gamerconnectapi.matchmaking.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    public ResponseEntity<Game> createGame(@Valid @RequestBody CreateGameDTO createGameDTO) {
        var game = gameService.createGame(
                createGameDTO.title(), createGameDTO.publisher(), createGameDTO.releaseYear());

        var location = URI.create("/games/" + game.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .body(game);
    }
}
