package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.PartyService;
import com.aleia.aleiaIactaEst.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.hamcrest.Matchers.containsInAnyOrder;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class PartyControllerIntegrationTests extends IntegrationTestBase {

    private final PlayerService playerService;
    private final PartyService partyService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Mapper<PartyEntity, PartyDto> partyMapper;

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
                containsInAnyOrder(
                        party.getPlayers().stream().map(PlayerEntity::getId).toArray(Integer[]::new)
                )
        )).andExpect(MockMvcResultMatchers.jsonPath("$.players[*].name").value(
                containsInAnyOrder(
                        party.getPlayers().stream().map(PlayerEntity::getName).toArray(String[]::new)
                )
        ));
    }

    @Test
    public void testThatListPartiesReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/party")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListPartiesReturnsListOfParties() throws Exception {
        PlayerEntity playerEntityA = createPlayer(TestDataUtil.createTestPlayerEntityA());
        PlayerEntity playerEntityB = createPlayer(TestDataUtil.createTestPlayerEntityB());
        PlayerEntity playerEntityC = createPlayer(TestDataUtil.createTestPlayerEntityC());

        Set<PlayerEntity> players = Set.of(playerEntityB, playerEntityA);
        Set<PlayerEntity> playersB = Set.of(playerEntityB, playerEntityC);

        PartyEntity party = createParty(players);
        PartyEntity partyB = createPartyB(playersB);

        PartyDto partyDto = partyMapper.mapTo(party);
        PartyDto partyDtoB = partyMapper.mapTo(partyB);

        var response = mockMvc.perform(MockMvcRequestBuilders.get("/party")).andReturn();

        then(response.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedResponse = List.of(partyDto, partyDtoB);
        var expected  = objectMapper.writeValueAsString(expectedResponse);
        then(response.getResponse().getContentAsString()).isEqualTo(expected);
    }

    @Test
    public void testThatGetPartyReturnsStatus200IfPartyExists() throws Exception {
        PlayerEntity player = createPlayer(TestDataUtil.createTestPlayerEntityC());
        Set<PlayerEntity> players = Set.of(player);
        PartyEntity party = createParty(players);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/party/" + party.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetPartyReturnsStatus404IfPartyDoesntExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/party/44")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdatePartyReturnsHttpStatus404IfPartyDoesntExist() throws Exception {
        PlayerEntity playerEntity = createPlayer(TestDataUtil.createTestPlayerEntityB());
        Set<PlayerEntity> newPlayersSet = Set.of(playerEntity);
        String newPlayersJson = objectMapper.writeValueAsString(newPlayersSet);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/party/44/addPlayers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPlayersJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdatePartyReturnsHttpStatus200IfPartyExists() throws Exception {
        PlayerEntity savedPlayer = createPlayer(TestDataUtil.createTestPlayerEntityA());
        Set<PlayerEntity> players = Set.of(savedPlayer);
        PartyEntity partyEntity = createParty(players);
        PlayerEntity newPlayer = createPlayer(TestDataUtil.createTestPlayerEntityB());
        Set<PlayerEntity> newPlayersSet = Set.of(newPlayer);
        String playerEntityJson = objectMapper.writeValueAsString(newPlayersSet);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/party/" + partyEntity.getId() + "/addPlayers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerEntityJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatAddPlayersAddsPlayersToParty() throws Exception{
        PlayerEntity player = createPlayer(TestDataUtil.createTestPlayerEntityC());
        PlayerEntity playerB = createPlayer(TestDataUtil.createTestPlayerEntityB());
        Set<PlayerEntity> players = Set.of(player, playerB);
        PartyEntity partyEntity = createParty(players);

        PlayerDto newPlayer = TestDataUtil.createTestPlayerDtoA();
        Set<PlayerDto> newPlayersSet = Set.of(newPlayer);
        String newPlayersJson = objectMapper.writeValueAsString(newPlayersSet);



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

    private PartyEntity createPartyB(Set<PlayerEntity> players) {
        PartyEntity partyEntity = TestDataUtil.createTestPartyEntityB(players);
        partyEntity.setId(null);

        return partyService.save(partyEntity);
    }
}
