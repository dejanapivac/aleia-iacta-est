package com.aleia.aleiaIactaEst;

import com.aleia.aleiaIactaEst.domain.dto.CampaignDto;
import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.*;
import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
import com.aleia.aleiaIactaEst.repositories.CampaignRepository;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import com.aleia.aleiaIactaEst.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class TestDataUtil {

    private final PlayerRepository playerRepository;
    private final PartyRepository partyRepository;
    private final CampaignRepository campaignRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public TestDataUtil(PlayerRepository playerRepository, PartyRepository partyRepository, CampaignRepository campaignRepository, SessionRepository sessionRepository) {
        this.playerRepository = playerRepository;
        this.partyRepository = partyRepository;
        this.campaignRepository = campaignRepository;
        this.sessionRepository = sessionRepository;
    }

    public static PlayerEntity createTestPlayerEntityA() {
        return PlayerEntity.builder()
                .id(null)
                .name("Cora")
                .build();
    }

    public static PlayerEntity createTestPlayerEntityB() {
        return PlayerEntity.builder()
                .id(null)
                .name("Roscoe")
                .build();
    }

    public static PlayerEntity createTestPlayerEntityC() {
        return PlayerEntity.builder()
                .id(3)
                .name("Dell")
                .build();
    }

    public static PlayerDto createTestPlayerDtoA() {
        return PlayerDto.builder()
                .id(1)
                .name("Cora")
                .build();
    }

    public static PartyEntity createTestPartyEntityA(Set<PlayerEntity> players) {
        return PartyEntity.builder()
                .id(1)
                .name("Skvadrica")
                .players(players)
                .build();
    }

    public static PartyEntity createTestPartyEntityB(Set<PlayerEntity> players) {
        return PartyEntity.builder()
                .id(1)
                .name("D&D drustvance")
                .players(players)
                .build();
    }

    public PlayerEntity savePlayer(PlayerEntity player) {
        return playerRepository.save(player);
    }

    public CampaignEntity saveCampaign(CampaignEntity campaign) {
        return campaignRepository.save(campaign);
    }
    public SessionEntity saveSession(SessionEntity session) {
        return sessionRepository.save(session);
    }
    public PartyEntity saveParty(PartyEntity party) {
        return partyRepository.save(party);
    }

    public PartyEntity createParty() {
        PlayerEntity playerA = createTestPlayerEntityA();
        playerA.setId(null);
        playerRepository.save(playerA);
        PlayerEntity playerB = createTestPlayerEntityB();
        playerB.setId(null);
        playerRepository.save(playerB);
        PlayerEntity playerC = createTestPlayerEntityC();
        playerC.setId(null);
        playerRepository.save(playerC);
        Set<PlayerEntity> players = Set.of(playerA, playerB, playerC);

        PartyEntity party = createTestPartyEntityA(players);
        party.setId(null);

        return partyRepository.save(party);
    }

    public RollEntity prepareRollState() {
        PlayerEntity playerA = savePlayer(createTestPlayerEntityA());
        PlayerEntity playerB = savePlayer(createTestPlayerEntityB());
        Set<PlayerEntity> players = Set.of(playerA, playerB);

        PartyEntity partyEntity = createTestPartyEntityA();
        partyEntity.setId(null);
        partyEntity.setPlayers(players);
        PartyEntity savedParty = saveParty(partyEntity);

        CampaignEntity campaignEntity = createTestCampaignB();
        campaignEntity.setId(null);
        campaignEntity.setParty(savedParty);
        CampaignEntity savedCampaign = saveCampaign(campaignEntity);

        SessionEntity session = createSessionEntity();
        session.setCampaign(savedCampaign);
        SessionEntity savedSession = saveSession(session);

        PlayerEntity player = partyEntity.getPlayers().stream().findFirst().get();

        return RollEntity.builder()
                .id(1)
                .player(player)
                .session(savedSession)
                .diceRollOption(null)
                .build();
    }

    public static PartyEntity createTestPartyEntityA() {
        return PartyEntity.builder()
                .id(1)
                .name("D&D grupica")
                .players(null)
                .build();
    }

    public static PartyDto createTestPartyDtoA() {
        return PartyDto.builder()
                .id(1)
                .name("Skvadrica")
                .players(Set.of(PlayerDto.builder()
                                .id(1)
                                .name("Cora")
                                .build(),
                        PlayerDto.builder()
                                .id(2)
                                .name("Roscoe")
                                .build()))
                .build();
    }

    public static CampaignEntity createTestCampaignA() {
        return CampaignEntity.builder()
                .id(1)
                .title("D&D")
                .createdAt(LocalDateTime.now())
                .party(null)
                .build();
    }

    public static CampaignEntity createFullTestCampaignA() {
        return CampaignEntity.builder()
                .id(1)
                .title("Full campaign")
                .createdAt(LocalDateTime.now())
                .party(createTestPartyEntityA())
                .build();
    }

    public static CampaignEntity createTestCampaignB() {
        return CampaignEntity.builder()
                .id(null)
                .title("Phandelver and Below")
                .createdAt(LocalDateTime.now())
                .party(null)
                .build();
    }

    public static CampaignDto createTestCampaignDtoA() {
        return CampaignDto.builder()
                .id(1)
                .title("Skvadron")
                .partyId(createTestPartyDtoA().getId())
                .build();
    }

    //TODO moze li null
    public static SessionEntity createSessionEntity() {
        return SessionEntity.builder()
                .id(null)
                .campaign(null)
                .playedAt(LocalDateTime.now())
                .build();
    }

//    public static RollEntity crateTestRollEntityA() {
//        return RollEntity.builder()
//                .id(1)
//                .player(createTestPlayerEntityA())
//                .campaign(createFullTestCampaignA())
//                .diceRollOption(DiceRollOption.TWENTY)
//                .build();
//    }
}
