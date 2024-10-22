package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import com.aleia.aleiaIactaEst.services.PlayerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class PartyControllerIntegrationTests extends IntegrationTestBase {

    private final PlayerService playerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatCreatePartyReturnsHttpStatusCode201Created() throws Exception {
        PlayerEntity playerEntity = TestDataUtil.createTestPlayerEntityA();
        playerEntity.setId(null);
        PlayerEntity savedPlayerA = playerService.save(playerEntity);

        PlayerEntity playerEntity2 = TestDataUtil.createTestPlayerEntityB();
        playerEntity2.setId(null);
        PlayerEntity savedPlayerB = playerService.save(playerEntity2);


        Set<PlayerEntity> players = Set.of(savedPlayerA, savedPlayerB);

        PartyEntity partyEntity = TestDataUtil.createTestPartyEntityA(players);
        partyEntity.setId(null);
        String partyJson = objectMapper.writeValueAsString(partyEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/party")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partyJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
