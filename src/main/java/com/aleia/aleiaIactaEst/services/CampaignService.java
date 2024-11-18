package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.CampaignEntity;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    CampaignEntity save(CampaignEntity campaignEntity);

    List<CampaignEntity> list();

    Optional<CampaignEntity> findById(Integer campaignId);

    Optional<CampaignEntity> update(CampaignEntity campaignEntity, Integer id);

    void delete(Integer id);

    Integer getPlayerAttends(Integer id);
}
