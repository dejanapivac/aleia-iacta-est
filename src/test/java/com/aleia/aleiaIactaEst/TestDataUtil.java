package com.aleia.aleiaIactaEst;

import com.aleia.aleiaIactaEst.domain.dto.GameDto;
import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class TestDataUtil {

    private final PlayerRepository playerRepository;
    private final PartyRepository partyRepository;

    @Autowired
    public TestDataUtil(PlayerRepository playerRepository, PartyRepository partyRepository) {
        this.playerRepository = playerRepository;
        this.partyRepository = partyRepository;
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

    public PartyEntity createParty() {
        PlayerEntity playerA = savePlayer(createTestPlayerEntityA());
        PlayerEntity playerB = savePlayer(createTestPlayerEntityB());
        PlayerEntity playerC = savePlayer(createTestPlayerEntityC());
        Set<PlayerEntity> players = Set.of(playerA, playerB, playerC);

        PartyEntity party = createTestPartyEntityA(players);

        return partyRepository.save(party);
    }

    public static PartyDto createTestPartyDtoA(Set<PlayerDto> players) {
        return PartyDto.builder()
                .id(1)
                .name("D&D Dto")
                .players(players)
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
                                .build()
                )).build();
    }

    public static GameEntity createTestGameA() {
        return GameEntity.builder()
                .id(1)
                .title("D&D")
                .createdAt(LocalDateTime.now())
                .build();
    }

}
