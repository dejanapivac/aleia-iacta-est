package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@AllArgsConstructor
public class PartyServiceTests extends IntegrationTestBase {

    private final PlayerService playerService;
    private final PartyService partyService;
    private final TestDataUtil testDataUtil;
    private final PartyRepository partyRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testThatAddPlayersAddsPlayersToParty() {
        PlayerEntity playerA = createPlayer(TestDataUtil.createTestPlayerEntityA());
        PlayerEntity playerB = createPlayer(TestDataUtil.createTestPlayerEntityB());
        Set<PlayerEntity> players = Set.of(playerA, playerB);
        PartyEntity partyEntity = createParty(players);

        PlayerEntity playerC = createPlayer(TestDataUtil.createTestPlayerEntityC());
        Set<PlayerEntity> newPlayersSet = Set.of(playerC);

        partyService.addPlayers(newPlayersSet, partyEntity.getId());

        // Posto je test smijem get() jer sigurno postoji taj party (gore sam ga napravila. inace bi morala map()
        PartyEntity expectedParty = partyRepository.findById(partyEntity.getId()).get();
        then(expectedParty.getPlayers()).isEqualTo(Set.of(playerA, playerB, playerC));
    }

    @Test
    public void testThatRemovePlayersRemovesPlayers() {
//        nije instanca klase, staticka klasa
        var party = testDataUtil.createParty();

        Set<Integer> playersToDelete = Set.of(1, 3);

        partyService.deletePlayers(playersToDelete, party.getId());

        PartyEntity expectedParty = partyRepository.findById(party.getId()).get();
        then(expectedParty.getPlayers().stream().map(PlayerEntity::getId)).isEqualTo(List.of(2));
    }

    @Test
    public void testThatUpdatePartyReturnsUpdatedParty() {
        PartyEntity party = testDataUtil.createParty();

        party.setName("Grupica");

        partyService.update(party, party.getId());

        PartyEntity expectedParty = partyRepository.findById(party.getId()).get();
        then(expectedParty.getName()).isEqualTo("Grupica");
    }

    @Test
    public void testThatDeletePartyDeletesParty() {
        PartyEntity party = testDataUtil.createParty();

        partyService.delete(party.getId());

        Optional<PartyEntity> deletedParty = partyRepository.findById(party.getId());
        then(deletedParty).isEmpty();
    }

    private PlayerEntity createPlayer(PlayerEntity player) {
        player.setId(null);

        return playerService.save(player);
    }

    private PartyEntity createParty(Set<PlayerEntity> players) {
        PartyEntity partyEntity = TestDataUtil.createTestPartyEntityA(players);
        partyEntity.setId(null);

        return partyService.save(partyEntity);
    }
}
