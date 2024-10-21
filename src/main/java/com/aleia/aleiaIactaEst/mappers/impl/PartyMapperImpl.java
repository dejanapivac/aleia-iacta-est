package com.aleia.aleiaIactaEst.mappers.impl;

import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PartyMapperImpl implements Mapper<PartyEntity, PartyDto> {

    private ModelMapper modelMapper;

    public PartyMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PartyDto mapTo(PartyEntity partyEntity) {
        return modelMapper.map(partyEntity, PartyDto.class);
    }

    @Override
    public PartyEntity mapFrom(PartyDto partyDto) {
        return modelMapper.map(partyDto, PartyEntity.class);
    }
}
