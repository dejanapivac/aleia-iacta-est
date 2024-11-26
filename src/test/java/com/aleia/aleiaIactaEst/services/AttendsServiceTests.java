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
    public void testThatFindAttendBySessionAndPlayerId() {
        PartyEntity party = testDataUtil.createParty();
        PlayerEntity player = party.getPlayers().stream().findFirst().get();
        CampaignEntity campaign = TestDataUtil.createTestCampaignB();
        campaign.setParty(party);
        CampaignEntity savedCampaign = testDataUtil.saveCampaign(campaign);
        SessionEntity session = TestDataUtil.createSessionEntity();
        session.setCampaign(savedCampaign);
        SessionEntity savedSession = testDataUtil.saveSession(session);
        AttendsEntity attend = testDataUtil.createAttend();
        attend.setId(new AttendsEntityId(savedSession.getId(), player.getId()));
        attend.setSession(savedSession);
        attend.setPlayer(player);
        attend.setAttend(true);
        AttendsEntity savedAttend = attendsService.save(attend);

        AttendsEntity expectedAttend = attendsRepository.findById(savedAttend.getId()).get();
        then(expectedAttend).isEqualTo(savedAttend);
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
        PlayerEntity player = party.getPlayers().stream().findFirst().get();
        CampaignEntity campaignA = TestDataUtil.createTestCampaignB();
        campaignA.setParty(party);
        CampaignEntity savedCampaignA = testDataUtil.saveCampaign(campaignA);
        CampaignEntity campaignB = TestDataUtil.createTestCampaignC();
        campaignB.setParty(party);
        CampaignEntity savedCampaignB = testDataUtil.saveCampaign(campaignB);
        SessionEntity sessionA = TestDataUtil.createSessionEntity();
        sessionA.setCampaign(savedCampaignA);
        SessionEntity savedSessionA = testDataUtil.saveSession(sessionA);
        SessionEntity sessionB = TestDataUtil.createSessionEntity();
        sessionB.setCampaign(savedCampaignB);
        SessionEntity savedSessionB = testDataUtil.saveSession(sessionB);
        SessionEntity sessionC = TestDataUtil.createSessionEntity();
        sessionC.setCampaign(savedCampaignA);
        SessionEntity savedSessionC = testDataUtil.saveSession(sessionC);

        AttendsEntity attendA = testDataUtil.createAttend();
        attendA.setId(new AttendsEntityId(savedSessionA.getId(), player.getId()));
        attendA.setSession(savedSessionA);
        attendA.setPlayer(player);
        attendA.setAttend(true);
        AttendsEntity savedAttendA = attendsService.save(attendA);
        AttendsEntity attendB = testDataUtil.createAttend();
        attendB.setId(new AttendsEntityId(savedSessionB.getId(), player.getId()));
        attendB.setSession(savedSessionB);
        attendB.setPlayer(player);
        attendB.setAttend(true);
        AttendsEntity savedAttendB = attendsService.save(attendB);
        AttendsEntity attendC = testDataUtil.createAttend();
        attendC.setId(new AttendsEntityId(savedSessionC.getId(), player.getId()));
        attendC.setPlayer(player);
        attendC.setSession(savedSessionC);
        attendC.setAttend(true);
        AttendsEntity savedAttendC = attendsService.save(attendC);


        List<AttendsEntity> playerAttendsList = List.of(savedAttendA, savedAttendB, savedAttendC);

        List<AttendsEntity> playersAttends = attendsService.findByPlayerId(player.getId());
        then(playersAttends.containsAll(playerAttendsList));
    }

    @Test
    public void testThatEditAttendEditsThatAttend() {
        PartyEntity party = testDataUtil.createParty();
        PlayerEntity player = party.getPlayers().stream().findFirst().get();
        CampaignEntity campaignA = TestDataUtil.createTestCampaignB();
        campaignA.setParty(party);
        CampaignEntity savedCampaignA = testDataUtil.saveCampaign(campaignA);
        SessionEntity sessionA = TestDataUtil.createSessionEntity();
        sessionA.setCampaign(savedCampaignA);
        SessionEntity savedSessionA = testDataUtil.saveSession(sessionA);
        AttendsEntity attendA = testDataUtil.createAttend();
        attendA.setId(new AttendsEntityId(savedSessionA.getId(), player.getId()));
        attendA.setSession(savedSessionA);
        attendA.setPlayer(player);
        attendA.setAttend(false);
        AttendsEntity savedAttendA = attendsService.save(attendA);

        savedAttendA.setAttend(true);
        attendsService.updateBySessionIdAndPlayerId(savedAttendA, savedAttendA.getId());

        AttendsEntity expectedAttend = attendsRepository.findById(savedAttendA.getId()).get();
        then(expectedAttend.getAttend()).isEqualTo(true);
    }
}
