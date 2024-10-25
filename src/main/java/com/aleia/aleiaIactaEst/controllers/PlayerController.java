package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import com.aleia.aleiaIactaEst.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    private Mapper<PlayerEntity, PlayerDto> playerMapper;

    public PlayerController(PlayerService playerService, Mapper<PlayerEntity, PlayerDto> playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto player) {
        PlayerEntity playerEntity = playerMapper.mapFrom(player);
        PlayerEntity savedPlayerEntity = playerService.save(playerEntity);
        return new ResponseEntity<>(playerMapper.mapTo(savedPlayerEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PlayerDto> listPlayers() {
        List<PlayerEntity> players = playerService.findAll();
        return players.stream()
                .map(playerMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("id") Integer id) {
        Optional<PlayerEntity> foundPlayer = playerService.findOne(id);
        return foundPlayer.map(playerEntity -> {
            PlayerDto playerDto = playerMapper.mapTo(playerEntity);
            return new ResponseEntity<>(playerDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> fullUpdatePlayer(
            @PathVariable("id") Integer id,
            @RequestBody PlayerDto playerDto
    ) {
        PlayerEntity playerEntity = playerMapper.mapFrom(playerDto);
        Optional<PlayerEntity> savedPlayerEntity = playerService.update(playerEntity, id);

        return savedPlayerEntity.map(player -> {
            PlayerDto savedPlayerDto = playerMapper.mapTo(player);
            return new ResponseEntity<>(savedPlayerDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDto> partialUpdate(
            @PathVariable("id") Integer id,
            @RequestBody PlayerDto playerDto) {

        if (!playerService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PlayerEntity playerEntity = playerMapper.mapFrom(playerDto);
        PlayerEntity updatedPlayer = playerService.partialUpdate(id, playerEntity);
        return new ResponseEntity<>(
                playerMapper.mapTo(updatedPlayer),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletePlayer(@PathVariable("id") Integer id) {

        playerService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
