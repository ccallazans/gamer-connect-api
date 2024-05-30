package com.gamerconnect.gamerconnectapi.services;

import com.gamerconnect.gamerconnectapi.entity.Game;
import com.gamerconnect.gamerconnectapi.exceptions.NotFoundException;
import com.gamerconnect.gamerconnectapi.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(String title, String publisher, Long releaseYear) {
        var game = Game.builder()
                .title(title)
                .publisher(publisher)
                .releaseYear(releaseYear)
                .build();

        return gameRepository.save(game);
    }

    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            throw new NotFoundException("Game not found with id: " + id);
        }

        return game.get();
    }
}
