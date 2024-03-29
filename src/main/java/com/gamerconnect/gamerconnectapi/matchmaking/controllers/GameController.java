package com.gamerconnect.gamerconnectapi.matchmaking.controllers;

import com.gamerconnect.gamerconnectapi.matchmaking.controllers.dto.CreateGameDTO;
import com.gamerconnect.gamerconnectapi.matchmaking.entity.Game;
import com.gamerconnect.gamerconnectapi.matchmaking.services.GameService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@Log4j2
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

    @GetMapping
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal(); // TESTE
    }

    @GetMapping("/")
    public String getGame(Authentication authentication) {
        log.info(authentication);
        return "Rocket League";
    }
}
