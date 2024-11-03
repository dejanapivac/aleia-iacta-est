package com.aleia.aleiaIactaEst;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@SpringBootTest(classes = {AleiaIactaEstApplication.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTestBase {

    @ServiceConnection
    private static final JdbcDatabaseContainer<?> postgresql = new PostgreSQLContainer<>("postgres:17.0")
            .withReuse(true)
            .withDatabaseName("testAleia");

    static {
        postgresql.start();
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private List<JpaRepository<?,?>> repositories;

    @Autowired
    private List<CrudRepository<?,?>> crudRepositories;

    @AfterEach
    void afterEach() {
        repositories.forEach(CrudRepository::deleteAll);
        crudRepositories.forEach(CrudRepository::deleteAll);
    }
}
