package com.aleia.aleiaIactaEst;

import com.aleia.aleiaIactaEst.domain.dto.GameDto;
import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

import java.util.List;
import java.util.Set;

public class TestDataUtil {

    private TestDataUtil() {
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

    public static GameDto createTestGameA() {
        return GameDto.builder()
                .id(1)
                .title("D&D")
                .build();
    }
}
