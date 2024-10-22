package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class PartyControllerIntegrationTests extends IntegrationTestBase {

    private final PlayerService playerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatCreatePartyReturnsHttpStatusCode201Created() throws Exception {
        PlayerEntity playerEntity = createPlayer(TestDataUtil.createTestPlayerEntityA());

        Set<PlayerEntity> players = Set.of(playerEntity);

        PartyEntity partyEntity = createParty(players);
        String partyJson = objectMapper.writeValueAsString(partyEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partyJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreatePartyReturnsCreatedParty() throws Exception {
        PlayerEntity playerA = createPlayer(TestDataUtil.createTestPlayerEntityA());
        PlayerEntity playerB = createPlayer(TestDataUtil.createTestPlayerEntityB());

        Set<PlayerEntity> partySet = Set.of(playerA, playerB);
        PartyEntity party = createParty(partySet);

        String partyEntityJson = objectMapper.writeValueAsString(party);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partyEntityJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Skvadrica")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.players[*].id").value(
                org.hamcrest.Matchers.containsInAnyOrder(
                        party.getPlayers().stream().map(PlayerEntity::getId).toArray(Integer[]::new)
                )
        )).andExpect(MockMvcResultMatchers.jsonPath("$.players[*].name").value(
                org.hamcrest.Matchers.containsInAnyOrder(
                        party.getPlayers().stream().map(PlayerEntity::getName).toArray(String[]::new)
                )
        ));
    }

    @Test
    public void testThatListPartiesReturnsHttpStatus200() throws Exception {
//        PlayerEntity playerA = createPlayer(TestDataUtil.createTestPlayerEntityA());
//        PlayerEntity playerB = createPlayer(TestDataUtil.createTestPlayerEntityB());
//        PlayerEntity playerC = createPlayer(TestDataUtil.createTestPlayerEntityC());
//
//        Set<PlayerEntity> partySetA = Set.of(playerA, playerB);
//        Set<PlayerEntity> partySetB = Set.of(playerB, playerC);
//
//        PartyEntity partyA = createParty(partySetA);
//        PartyEntity partyB = createParty(partySetB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/party")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private PlayerEntity createPlayer(PlayerEntity player) {
        player.setId(null);

        return playerService.save(player);
    }

    private PartyEntity createParty(Set<PlayerEntity> players) {
        PartyEntity partyEntity = TestDataUtil.createTestPartyEntityA(players);
        partyEntity.setId(null);

        return partyEntity;
    }

//    private
}
