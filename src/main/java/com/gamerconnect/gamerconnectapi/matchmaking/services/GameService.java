package com.gamerconnect.gamerconnectapi.matchmaking.services;

import com.gamerconnect.gamerconnectapi.exceptions.BusinessException;
import com.gamerconnect.gamerconnectapi.matchmaking.entity.Game;
import com.gamerconnect.gamerconnectapi.matchmaking.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(String title, String publisher, Long relaseYear) {
        validateTitle(title);

        var game = Game.builder()
                .title(title)
                .publisher(publisher)
                .releaseYear(relaseYear)
                .build();

        return gameRepository.save(game);
    }

    private void validateTitle(String title) {
        if (title.isEmpty()) {
            throw new BusinessException(String.format("invalid title name: %s", title));
        }

        if (gameRepository.existsByTitle(title)) {
            throw new BusinessException(String.format("game title already exists: %s", title));
        }
    }
}
