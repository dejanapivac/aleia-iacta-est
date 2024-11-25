package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.*;
import com.aleia.aleiaIactaEst.repositories.AttendsRepository;
import com.aleia.aleiaIactaEst.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@RequiredArgsConstructor
public class SessionServiceTests extends IntegrationTestBase {

    private final SessionService sessionService;
    private final SessionRepository sessionRepository;
    private final AttendsService attendsService;
    private final TestDataUtil testDataUtil;

    @Test
    public void testThatCreateSessionCreatesThatSession() {
        PlayerEntity playerA = TestDataUtil.createTestPlayerEntityA();
        PlayerEntity savedPlayerA = testDataUtil.savePlayer(playerA);
        PlayerEntity playerB = TestDataUtil.createTestPlayerEntityB();
        PlayerEntity savedPlayerB = testDataUtil.savePlayer(playerB);
        Set<PlayerEntity> playerEntitySet = Set.of(savedPlayerA, savedPlayerB);
        PartyEntity party = TestDataUtil.createTestPartyEntityA();
        party.setPlayers(playerEntitySet);
        PartyEntity savedParty = testDataUtil.saveParty(party);
        CampaignEntity campaign = TestDataUtil.createTestCampaignB();
        campaign.setParty(savedParty);
        CampaignEntity savedCampaign = testDataUtil.saveCampaign(campaign);
        SessionEntity session = TestDataUtil.createSessionEntity();
        session.setCampaign(savedCampaign);
        SessionEntity savedSession = sessionService.save(session);

        SessionEntity expectedSession = sessionRepository.findById(savedSession.getId()).get();
        then(expectedSession.getCampaign()).isEqualTo(savedCampaign);
    }

    @Test
    public void testThatFindByIdReturnsSessionWithThatId() {
        PartyEntity party = testDataUtil.createParty();
        CampaignEntity campaign = TestDataUtil.createTestCampaignB();
        campaign.setParty(party);
        CampaignEntity savedCampaign = testDataUtil.saveCampaign(campaign);
        SessionEntity session = TestDataUtil.createSessionEntity();
        session.setCampaign(savedCampaign);
        SessionEntity savedSession = sessionRepository.save(session);

        SessionEntity expectedSession = sessionService.findById(savedSession.getId()).get();
        then(expectedSession).isEqualTo(savedSession);
    }

    @Test
    public void testThatDeleteSessionDeletesThatSession() {
        PartyEntity party = testDataUtil.createParty();
        CampaignEntity campaign = TestDataUtil.createTestCampaignB();
        campaign.setParty(party);
        CampaignEntity savedCampaign = testDataUtil.saveCampaign(campaign);
        SessionEntity session = TestDataUtil.createSessionEntity();
        session.setCampaign(savedCampaign);
        SessionEntity savedSession = sessionRepository.save(session);

        SessionEntity expectedSession = sessionService.findById(savedSession.getId()).get();
        sessionService.deleteById(expectedSession.getId());
        Optional<SessionEntity> deletedSession = sessionService.findById(expectedSession.getId());
        then(deletedSession).isEmpty();
    }

}
