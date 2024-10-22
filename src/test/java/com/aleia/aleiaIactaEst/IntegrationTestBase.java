package com.aleia.aleiaIactaEst;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = {AleiaIactaEstApplication.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class IntegrationTestBase {

    @ServiceConnection
    private static final JdbcDatabaseContainer<?> postgresql = new PostgreSQLContainer<>("postgres:17.0")
            .withReuse(true)
            .withDatabaseName("testAleia");

    static {
        postgresql.start();
    }

}