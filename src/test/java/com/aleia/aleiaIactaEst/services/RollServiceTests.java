package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
import com.aleia.aleiaIactaEst.repositories.RollRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@RequiredArgsConstructor
public class RollServiceTests extends IntegrationTestBase {

    private final RollService rollService;

    private final RollRepository rollRepository;

    private final TestDataUtil testDataUtil;

    @Test
    public void testThatCreateRollCreatesRoll() {
        RollEntity rollEntity = testDataUtil.prepareRollState();
        rollEntity.setDiceRollOption(DiceRollOption.TWENTY);
        rollService.save(rollEntity);

        Optional<RollEntity> expectedRoll = rollRepository.findById(rollEntity.getId());
        then(expectedRoll.get()).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(rollEntity);
    }
}
