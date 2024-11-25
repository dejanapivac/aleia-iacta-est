package com.aleia.aleiaIactaEst.services;


import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.*;
import com.aleia.aleiaIactaEst.repositories.AttendsRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@RequiredArgsConstructor
public class AttendsServiceTests extends IntegrationTestBase {

    private final AttendsService attendsService;

    private final AttendsRepository attendsRepository;

    private final TestDataUtil testDataUtil;

    @Test
    public void testThatCreateAttendCreatesAnAttend() {
        AttendsEntity attend = testDataUtil.createFullAttend();
        attend.setAttend(true);
        AttendsEntity savedAttend = attendsRepository.save(attend);

        AttendsEntity expectedAttend = attendsService.findBySessionIdAndPlayerId(savedAttend.getSession().getId(), savedAttend.getPlayer().getId()).get();
        then(expectedAttend.getAttend()).isTrue();
    }

    @Test
    public void testThatGetSessionAttendsGetsAttends() {
        PartyEntity party = testDataUtil.createParty();
        CampaignEntity campaign = TestDataUtil.createTestCampaignB();
        campaign.setParty(party);
        CampaignEntity savedCampaign = testDataUtil.saveCampaign(campaign);
        SessionEntity session = TestDataUtil.createSessionEntity();
        session.setCampaign(savedCampaign);
        SessionEntity savedSession = testDataUtil.saveSession(session);

        Set<PlayerEntity> playersSet = party.getPlayers();

        for (PlayerEntity player : playersSet) {
            AttendsEntity attend = testDataUtil.createAttend();
            attend.setId(new AttendsEntityId(savedSession.getId(), player.getId()));
            attend.setSession(savedSession);
            attend.setPlayer(player);
            attend.setAttend(true);
            attendsService.save(attend);
        }

        List<AttendsEntity> attendsListBySessionId = attendsService.findBySessionId(savedSession.getId());
        then(attendsListBySessionId.containsAll(playersSet));
    }

    @Test
    public void testListAllPlayerAttendsListsAllAttends() {
        PartyEntity party = testDataUtil.createParty();
        CampaignEntity campaignA = TestDataUtil.createTestCampaignB();
        campaignA.setParty(party);
        CampaignEntity savedCampaign = testDataUtil.saveCampaign(campaignA);
        CampaignEntity campaignB = TestDataUtil.createTestCampaignC();
        campaignB.setParty(party);
        CampaignEntity savedCampaignB = testDataUtil.saveCampaign(campaignB);
        SessionEntity sessionA = TestDataUtil.createSessionEntity();
        sessionA.setCampaign(campaignA);

    }
}
