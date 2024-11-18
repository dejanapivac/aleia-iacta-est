package com.aleia.aleiaIactaEst;


import com.aleia.aleiaIactaEst.repositories.CampaignRepository;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.repositories.PlayerRepository;
import com.aleia.aleiaIactaEst.repositories.RollRepository;
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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest(classes = {AleiaIactaEstApplication.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTestBase {

//    private CampaignRepository campaignRepository;
//    private RollRepository rollRepository;
//    private PlayerRepository playerRepository;
//    private PartyRepository partyRepository;

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

    private static final List<Class<? extends JpaRepository<?, ?>>> DB_CLEAR_ORDER =
            List.of(RollRepository.class, CampaignRepository.class, PartyRepository.class, PlayerRepository.class);

    private static final Map<Class<? extends CrudRepository<?, ?>>, Integer> DB_CLEAR_ORDER_MAP =
            DB_CLEAR_ORDER.stream().collect(Collectors.toMap(Function.identity(), DB_CLEAR_ORDER::indexOf));

    private void clearDb() {
        repositories.stream()
                .sorted(Comparator.comparingInt(IntegrationTestBase::inDatabaseClearOrder))
                .forEach(CrudRepository::deleteAll);
    }

    private static int inDatabaseClearOrder(JpaRepository<?, ?> repository) {
        return DB_CLEAR_ORDER_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isAssignableFrom(repository.getClass()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(100);
    }

    //TODO
    @AfterEach
    void afterEach() {
        clearDb();

//        rollRepository.deleteAll();
//        campaignRepository.deleteAll();
//        partyRepository.deleteAll();
//        playerRepository.deleteAll();
    }

}
