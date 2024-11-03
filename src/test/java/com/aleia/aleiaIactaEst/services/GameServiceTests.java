package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@RequiredArgsConstructor
public class GameServiceTests extends IntegrationTestBase {

    private final GameService gameService;

    private final GameRepository gameRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatCreateGameCreatesAGame() {
        GameEntity gameEntity = TestDataUtil.createTestGameA();
        gameService.save(gameEntity);

        GameEntity expectedGame = gameRepository.findById(gameEntity.getId()).get();
        then(expectedGame.getTitle()).isEqualTo("D&D");
    }

    @Test
    public void testThatGetGamesReturnsAllGames() {
        GameEntity gameEntityA = TestDataUtil.createTestGameA();
        gameEntityA.setId(null);
        gameService.save(gameEntityA);
        GameEntity gameEntityB = TestDataUtil.createTestGameB();
        gameEntityB.setId(null);
        gameService.save(gameEntityB);

        List<GameEntity> games = gameService.list();
        List<GameEntity> expectedGames = List.of(gameEntityA, gameEntityB);
        then(games).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(expectedGames);
    }

    @Test
    public void testThatFindGameReturnsThatGameIfItExists() {
        GameEntity gameEntity = TestDataUtil.createTestGameA();
        gameEntity.setId(null);
        gameService.save(gameEntity);

        Optional<GameEntity> expectedGame = gameRepository.findById(gameEntity.getId());
        then(expectedGame.get()).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(gameEntity);
    }

    @Test
    public void testThatUpdateGameUpdatesGameIfItExists() {
        GameEntity gameEntity = TestDataUtil.createTestGameA();
        gameEntity.setId(null);
        gameService.save(gameEntity);

        String newTitle = "Konan barbarin";
        gameEntity.setTitle(newTitle);
        gameService.update(gameEntity, gameEntity.getId());
        Optional<GameEntity> expectedGame = gameRepository.findById(gameEntity.getId());
        then(expectedGame.get().getTitle()).isEqualTo(newTitle);
    }
}
