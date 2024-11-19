package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.CampaignEntity;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.repositories.CampaignRepository;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@RequiredArgsConstructor
public class CampaignServiceTests extends IntegrationTestBase {

    private final CampaignService campaignService;

    private final CampaignRepository campaignRepository;

    private final PartyRepository partyRepository;

    private final TestDataUtil testDataUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatCreateCampaignCreatesACampaign() {
        CampaignEntity campaignEntity = TestDataUtil.createTestCampaignA();
        CampaignEntity savedCampaign = campaignService.save(campaignEntity);

        CampaignEntity expectedCampaign = campaignRepository.findById(savedCampaign.getId()).get();
        then(expectedCampaign).isEqualTo(savedCampaign);
    }

    @Test
    public void testThatGetCampaignsReturnsAllCampaigns() {
        CampaignEntity campaignEntityA = TestDataUtil.createTestCampaignA();
        campaignEntityA.setId(null);
        campaignService.save(campaignEntityA);
        CampaignEntity campaignEntityB = TestDataUtil.createTestCampaignB();
        campaignEntityB.setId(null);
        campaignService.save(campaignEntityB);

        List<CampaignEntity> campaigns = campaignService.list();
        List<CampaignEntity> expectedCampaigns = List.of(campaignEntityA, campaignEntityB);
        then(campaigns).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(expectedCampaigns);
    }

    @Test
    public void testThatFindCampaignReturnsThatCampaignIfItExists() {
        CampaignEntity campaignEntity = TestDataUtil.createTestCampaignA();
        campaignEntity.setId(null);
        campaignService.save(campaignEntity);

        Optional<CampaignEntity> expectedCampaign = campaignRepository.findById(campaignEntity.getId());
        then(expectedCampaign.get()).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(campaignEntity);
    }

    @Test
    public void testThatUpdateCampaignUpdatesCampaignIfItExists() {
        CampaignEntity campaignEntity = TestDataUtil.createTestCampaignA();
        campaignEntity.setId(null);
        campaignService.save(campaignEntity);

        String newTitle = "Konan barbarin";
        campaignEntity.setTitle(newTitle);
        campaignService.update(campaignEntity, campaignEntity.getId());
        Optional<CampaignEntity> expectedCampaign = campaignRepository.findById(campaignEntity.getId());
        then(expectedCampaign.get().getTitle()).isEqualTo(newTitle);
    }

    @Test
    public void testThatDeleteCampaignDeletesThatCampaign() {
        CampaignEntity campaignEntity = TestDataUtil.createTestCampaignA();
        campaignEntity.setId(null);
        CampaignEntity savedCampaign = campaignService.save(campaignEntity);

        campaignService.delete(savedCampaign.getId());
        Optional<CampaignEntity> expectedCampaign = campaignService.findById(savedCampaign.getId());
        then(expectedCampaign).isEmpty();
    }

    @Test
    public void testThatGetPlayerAttendsGetsPlayersAttends() {
        CampaignEntity campaignEntity = TestDataUtil.createTestCampaignA();
        campaignEntity.setId(null);
        PartyEntity party = testDataUtil.createParty();
        party.setId(null);
        PartyEntity savedParty = testDataUtil.saveParty(party);
        campaignEntity.setParty(savedParty);
        campaignRepository.save(campaignEntity);
        CampaignEntity expectedCampaign = campaignService.findById(campaignEntity.getId()).get();
        PlayerEntity player = expectedCampaign.getParty().getPlayers().stream().findFirst().get();

        Integer playerAttends = campaignService.getPlayerAttends(player.getId());
    }
}
