package com.aleia.aleiaIactaEst;

import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

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
}
