package com.aleia.aleiaIactaEst;

import com.aleia.aleiaIactaEst.domain.dto.GameDto;
import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
import com.aleia.aleiaIactaEst.repositories.GameRepository;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Component
public class TestDataUtil {

    private final PlayerRepository playerRepository;
    private final PartyRepository partyRepository;

    private final GameRepository gameRepository;

    @Autowired
    public TestDataUtil(PlayerRepository playerRepository, PartyRepository partyRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.partyRepository = partyRepository;
        this.gameRepository = gameRepository;
    }

    public static PlayerEntity createTestPlayerEntityA() {
        return PlayerEntity.builder()
                .id(1)
                .name("Cora")
                .build();
    }

    public static PlayerEntity createTestPlayerEntityB() {
        return PlayerEntity.builder()
                .id(2)
                .name("Roscoe")
                .build();
    }

    public static PlayerEntity createTestPlayerEntityC() {
        return PlayerEntity.builder()
                .id(3)
                .name("Dell")
                .build();
    }

    public static PlayerDto createTestPlayerDtoA() {
        return PlayerDto.builder()
                .id(1)
                .name("Cora")
                .build();
    }

    public static PartyEntity createTestPartyEntityA(Set<PlayerEntity> players) {
        return PartyEntity.builder()
                .id(1)
                .name("Skvadrica")
                .players(players)
                .build();
    }

    public static PartyEntity createTestPartyEntityB(Set<PlayerEntity> players) {
        return PartyEntity.builder()
                .id(1)
                .name("D&D drustvance")
                .players(players)
                .build();
    }

    public PlayerEntity savePlayer(PlayerEntity player) {
        return playerRepository.save(player);
    }

    public GameEntity saveGame(GameEntity game) {
        return gameRepository.save(game);
    }

    public PartyEntity createParty() {
        PlayerEntity playerA = createTestPlayerEntityA();
        playerA.setId(null);
        playerRepository.save(playerA);
        PlayerEntity playerB = createTestPlayerEntityB();
        playerB.setId(null);
        playerRepository.save(playerB);
        PlayerEntity playerC = createTestPlayerEntityC();
        playerC.setId(null);
        playerRepository.save(playerC);
        Set<PlayerEntity> players = Set.of(playerA, playerB, playerC);

        PartyEntity party = createTestPartyEntityA(players);
        party.setId(null);

        return partyRepository.save(party);
    }

    public RollEntity prepareRollState() {
        PartyEntity partyEntity = createParty();
        GameEntity gameEntity = createFullTestGameA();
        gameEntity.setId(null);
        gameEntity.setParty(partyEntity);
        saveGame(gameEntity);

        PlayerEntity player = partyEntity.getPlayers().stream().findFirst().get();

        return RollEntity.builder()
                .id(1)
                .player(player)
                .game(gameEntity)
                .diceRollOption(null)
                .build();
    }

    public static PartyEntity createTestPartyEntityA() {
        return PartyEntity.builder()
                .id(1)
                .name("D&D Dto")
                .players(Set.of(PlayerEntity.builder()
                        .id(1)
                        .name("Cora")
                        .build()))
                .build();
    }

    public static PartyDto createTestPartyDtoA() {
        return PartyDto.builder()
                .id(1)
                .name("Skvadrica")
                .players(Set.of(PlayerDto.builder()
                                .id(1)
                                .name("Cora")
                                .build(),
                        PlayerDto.builder()
                                .id(2)
                                .name("Roscoe")
                                .build()))
                .build();
    }

    public static GameEntity createTestGameA() {
        return GameEntity.builder()
                .id(1)
                .title("D&D")
                .createdAt(LocalDateTime.now())
                .party(null)
                .build();
    }

    public static GameEntity createFullTestGameA() {
        return GameEntity.builder()
                .id(1)
                .title("Full game")
                .createdAt(LocalDateTime.now())
                .party(createTestPartyEntityA())
                .build();
    }

    public static GameEntity createTestGameB() {
        return GameEntity.builder()
                .id(2)
                .title("Ekipica")
                .createdAt(LocalDateTime.now())
                .party(null)
                .build();
    }

    public static GameDto createTestGameDtoA() {
        return GameDto.builder()
                .id(1)
                .title("Skvadron")
                .partyId(createTestPartyDtoA().getId())
                .build();
    }

    public static RollEntity crateTestRollEntityA() {
        return RollEntity.builder()
                .id(1)
                .player(createTestPlayerEntityA())
                .game(createFullTestGameA())
                .diceRollOption(DiceRollOption.TWENTY)
                .build();
    }
}
