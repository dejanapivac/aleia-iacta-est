package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTests {

    public PlayerService playerService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    public PlayerControllerIntegrationTests(MockMvc mockMvc, PlayerService playerService) {
        this.mockMvc = mockMvc;
        this.playerService = playerService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturnsHttp201Status() {

    }
}
