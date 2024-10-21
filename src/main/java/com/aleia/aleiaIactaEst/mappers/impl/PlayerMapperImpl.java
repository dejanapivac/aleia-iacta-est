package com.aleia.aleiaIactaEst.mappers.impl;

import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class PlayerMapperImpl implements Mapper<PlayerEntity, PlayerDto> {

    private ModelMapper modelMapper;

    public PlayerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerDto mapTo(PlayerEntity playerEntity) {
        return modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public PlayerEntity mapFrom(PlayerDto playerDto) {
        return modelMapper.map(playerDto, PlayerEntity.class);
    }
}
