package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import com.aleia.aleiaIactaEst.services.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerEntity save(PlayerEntity playerEntity) {
        return playerRepository.save(playerEntity);
    }

    @Override
    public List<PlayerEntity> findAll() {
        return StreamSupport.stream(playerRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    //TODO
    @Override
    public Optional<PlayerEntity> findOne(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return playerRepository.existsById(id);
    }
}
