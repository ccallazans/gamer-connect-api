package com.gamerconnect.gamerconnectapi.controllers;

import com.gamerconnect.gamerconnectapi.controllers.dto.CreateGameRequest;
import com.gamerconnect.gamerconnectapi.controllers.dto.CreateGameResponse;
import com.gamerconnect.gamerconnectapi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CreateGameResponse> createGame(@RequestBody @Valid CreateGameRequest createGameRequest) {
        var game = gameService.createGame(
                createGameRequest.title(),
                createGameRequest.publisher(),
                createGameRequest.releaseYear());

        return new ResponseEntity<>(CreateGameResponse.from(game), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateGameResponse> getGameById(@PathVariable Long id) {
        var game = gameService.getGameById(id);
        return new ResponseEntity<>(CreateGameResponse.from(game), HttpStatus.OK);
    }
}
