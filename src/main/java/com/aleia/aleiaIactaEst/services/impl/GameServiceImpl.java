package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.repositories.GameRepository;
import com.aleia.aleiaIactaEst.services.GameService;
import com.aleia.aleiaIactaEst.services.PlayerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

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
    public Optional<GameEntity> findById(Integer id) {
        return gameRepository.findById(id);
    }

    @Override
    public Optional<GameEntity> update(GameEntity gameEntity, Integer id) {
        return gameRepository.findById(id).map(expectedGame -> {
            Optional.ofNullable(gameEntity.getTitle()).ifPresent(expectedGame::setTitle);
            Optional.ofNullable(gameEntity.getParty()).ifPresent(expectedGame::setParty);
            return gameRepository.save(expectedGame);
        });
    }

    @Override
    public void delete(Integer id) {
        gameRepository.deleteById(id);
    }
}
