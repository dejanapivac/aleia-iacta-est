package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTests {

    public PlayerService playerService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public PlayerControllerIntegrationTests(MockMvc mockMvc, PlayerService playerService) {
        this.mockMvc = mockMvc;
        this.playerService = playerService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturnsHttp201Status() throws Exception {
        PlayerEntity testPlayerA = TestDataUtil.createTestPlayerEntityA();
        testPlayerA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testPlayerA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturnsCreatedPlayer() throws Exception {
        PlayerEntity testPlayerEntity = TestDataUtil.createTestPlayerEntityA();
        testPlayerEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(testPlayerEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Cora")
        );
    }

    @Test
    public void testThatListPlayersReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorsListsAuthors() throws Exception {
        PlayerEntity playerEntity = TestDataUtil.createTestPlayerEntityA();
        playerService.save(playerEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Cora")
        );
    }

    @Test
    public void testThatGetPlayerReturnsHttpStatus200IfPlayerExists() throws Exception {
        PlayerEntity testPlayerEntity = TestDataUtil.createTestPlayerEntityA();
        playerService.save(testPlayerEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetPlayerReturnsThatPlayer() throws Exception {
        PlayerEntity testPlayerEntity = TestDataUtil.createTestPlayerEntityA();
        playerService.save(testPlayerEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/" + testPlayerEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Cora")
        );
    }

    @Test
    public void testThatGetPlayerReturnsHttpStatus404IfPlayerDoesntExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/1")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdatePlayerReturnsHttpStatus404IfPlayerDoesntExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/player/32")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdatePlayerReturnsHttpStatus200IfPlayerExists() throws Exception {
        PlayerEntity testPlayerEntity = TestDataUtil.createTestPlayerEntityA();
        PlayerEntity savedPlayer = playerService.save(testPlayerEntity);

        PlayerDto playerDto = TestDataUtil.createTestPlayerDtoA();
        String playerDtoJson = objectMapper.writeValueAsString(playerDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/players/" + savedPlayer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdatePlayerUpdatesPlayer() throws Exception{
        PlayerEntity testPlayerEntity = TestDataUtil.createTestPlayerEntityA();
        PlayerEntity savedPlayer = playerService.save(testPlayerEntity);

        PlayerDto playerDto = PlayerDto.builder()
                .id(savedPlayer.getId())
                .name("Roscoe Jr.")
                .build();

        String playerDtoJson = objectMapper.writeValueAsString(playerDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/players/" + savedPlayer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Optional<PlayerEntity> updatedPlayer = playerService.findOne(savedPlayer.getId());
        String updatedPlayerName = updatedPlayer.get().getName();
        assertThat(updatedPlayerName).isEqualTo(playerDto.getName());
    }

}
