package com.aleia.aleiaIactaEst.mappers.impl;

import com.aleia.aleiaIactaEst.domain.dto.CampaignDto;
import com.aleia.aleiaIactaEst.domain.entities.CampaignEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapperImpl implements Mapper<CampaignEntity, CampaignDto> {

    private ModelMapper modelMapper;

    public CampaignMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CampaignDto mapTo(CampaignEntity campaignEntity) {
        return modelMapper.map(campaignEntity, CampaignDto.class);
    }

    @Override
    public CampaignEntity mapFrom(CampaignDto campaignDto) {
        return modelMapper.map(campaignDto, CampaignEntity.class);
    }
}
