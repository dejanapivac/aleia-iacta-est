package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.CampaignEntity;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.domain.entities.SessionEntity;
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
        campaign.setParty(party);
        CampaignEntity savedCamapaign = testDataUtil.saveCampaign(campaign);
        SessionEntity session = TestDataUtil.createSessionEntity();
        session.setCampaign(savedCamapaign);
        SessionEntity savedSession = sessionService.save(session);

        SessionEntity expectedSession = sessionRepository.findById(savedSession.getId()).get();
        then(expectedSession.getCampaign()).isEqualTo(savedCamapaign);
    }
}
