package com.gamerconnect.gamerconnectapi.controllers;

import com.gamerconnect.gamerconnectapi.controllers.dto.request.CreateGameRequest;
import com.gamerconnect.gamerconnectapi.controllers.dto.response.GameResponse;
import com.gamerconnect.gamerconnectapi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GameResponse> createGame(@RequestBody @Valid CreateGameRequest createGameRequest) {
        var game = gameService.createGame(
                createGameRequest.title(),
                createGameRequest.publisher(),
                createGameRequest.releaseYear());

        return new ResponseEntity<>(GameResponse.from(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GameResponse> updateGame(
            @PathVariable Long id,
            @RequestBody @Valid CreateGameRequest createGameRequest) {

        var game = gameService.updateGame(
                id,
                createGameRequest.title(),
                createGameRequest.publisher(),
                createGameRequest.releaseYear());

        return new ResponseEntity<>(GameResponse.from(game), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<GameResponse>> getALlGames() {
        var games = gameService.getAllGames();
        return new ResponseEntity<>(games.stream().map(GameResponse::from).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable Long id) {
        var game = gameService.getGameById(id);
        return new ResponseEntity<>(GameResponse.from(game), HttpStatus.OK);
    }
}
