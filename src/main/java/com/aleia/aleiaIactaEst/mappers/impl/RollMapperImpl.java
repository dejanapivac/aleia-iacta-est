package com.aleia.aleiaIactaEst.mappers.impl;

import com.aleia.aleiaIactaEst.domain.dto.RollDto;
import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.impl.RollServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RollMapperImpl implements Mapper<RollEntity, RollDto> {

    private ModelMapper modelMapper;

    public RollMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RollDto mapTo(RollEntity rollEntity) {
        return this.modelMapper.map(rollEntity, RollDto.class);
    }

    @Override
    public RollEntity mapFrom(RollDto rollDto) {
        return this.modelMapper.map(rollDto, RollEntity.class);
    }
}
