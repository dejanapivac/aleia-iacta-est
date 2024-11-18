package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.CampaignEntity;
import com.aleia.aleiaIactaEst.repositories.CampaignRepository;
import com.aleia.aleiaIactaEst.services.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    @Override
    public CampaignEntity save(CampaignEntity campaignEntity) {
        return campaignRepository.save(campaignEntity);
    }

    @Override
    public List<CampaignEntity> list() {
        return StreamSupport.stream(campaignRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CampaignEntity> findById(Integer id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Optional<CampaignEntity> update(CampaignEntity campaignEntity, Integer id) {
        return campaignRepository.findById(id).map(expectedCampaign -> {
            Optional.ofNullable(campaignEntity.getTitle()).ifPresent(expectedCampaign::setTitle);
            Optional.ofNullable(campaignEntity.getParty()).ifPresent(expectedCampaign::setParty);
            return campaignRepository.save(expectedCampaign);
        });
    }

    @Override
    public void delete(Integer id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public Integer getPlayerAttends(Integer id) {
        return null;
    }
}
