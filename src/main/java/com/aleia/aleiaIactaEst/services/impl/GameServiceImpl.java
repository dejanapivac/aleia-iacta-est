package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.repositories.GameRepository;
import com.aleia.aleiaIactaEst.services.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameEntity save(GameEntity gameEntity) {
        return gameRepository.save(gameEntity);
    }

    @Override
    public List<GameEntity> list() {
        return StreamSupport.stream(gameRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GameEntity> findOne(Integer id) {
        return gameRepository.findById(id);
    }

    @Override
    public Optional<GameEntity> update(GameEntity gameEntity, Integer id) {
        Optional<GameEntity> expectedGame = gameRepository.findById(id);
        return expectedGame.map(game -> {
            game.setTitle(gameEntity.getTitle());
            game.setParty(gameEntity.getParty());
            return gameRepository.save(game);
        });
    }
}
