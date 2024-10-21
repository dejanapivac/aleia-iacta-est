package com.aleia.aleiaIactaEst.mappers.impl;

import com.aleia.aleiaIactaEst.domain.dto.GameDto;
import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GameMapperImpl implements Mapper<GameEntity, GameDto> {

    private ModelMapper modelMapper;

    public GameMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GameDto mapTo(GameEntity gameEntity) {
        return modelMapper.map(gameEntity, GameDto.class);
    }

    @Override
    public GameEntity mapFrom(GameDto gameDto) {
        return modelMapper.map(gameDto, GameEntity.class);
    }
}
