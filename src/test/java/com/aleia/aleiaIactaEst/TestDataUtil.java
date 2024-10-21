package com.aleia.aleiaIactaEst;

import com.aleia.aleiaIactaEst.domain.dto.GameDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

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

    public static PlayerDto createTestPlayerDtoA() {
        return PlayerDto.builder()
                .id(1)
                .name("Cora")
                .build();
    }

    public static GameDto createTestGameA() {
        return GameDto.builder()
                .id(1)
                .title("D&D")
                .build();
    }
}
