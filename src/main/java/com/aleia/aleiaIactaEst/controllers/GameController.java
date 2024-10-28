package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.GameDto;
import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.repositories.GameRepository;
import com.aleia.aleiaIactaEst.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    private GameService gameService;


    private Mapper<GameEntity, GameDto> gameMapper;

    public GameController(GameService gameService, Mapper<GameEntity, GameDto> gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        GameEntity gameEntity = gameMapper.mapFrom(gameDto);
        GameEntity savedGameEntity = gameService.save(gameEntity);
        return new ResponseEntity<>(gameMapper.mapTo(savedGameEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<GameDto> listGames() {
        List<GameEntity> gameEntities = gameService.list();
        return gameEntities.stream()
                .map(gameEntity -> gameMapper.mapTo(gameEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable("id") Integer id) {
        Optional<GameEntity> expectedGame = gameService.findOne(id);
        return expectedGame.map(gameEntity -> {
            GameDto gameDto = gameMapper.mapTo(gameEntity);
            return new ResponseEntity<>(gameDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
