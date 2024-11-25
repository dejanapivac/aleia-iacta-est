package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.IntegrationTestBase;
import com.aleia.aleiaIactaEst.TestDataUtil;
import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
import com.aleia.aleiaIactaEst.repositories.RollRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
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
        rollEntity.setId(null);
        rollEntity.setDiceRollOption(DiceRollOption.TWENTY);
        rollService.save(rollEntity);

        Optional<RollEntity> expectedRoll = rollRepository.findById(rollEntity.getId());
        then(expectedRoll.get()).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(rollEntity);
    }

    @Test
    public void testThatGetRollGetsThatRoll() {
        RollEntity rollEntity = testDataUtil.prepareRollState();
        rollEntity.setId(null);
        rollEntity.setDiceRollOption(DiceRollOption.TWENTY);
        rollRepository.save(rollEntity);

        Optional<RollEntity> expectedRoll = rollRepository.findById(rollEntity.getId());
        then(expectedRoll.get()).usingRecursiveComparison().ignoringFieldsOfTypes(LocalDateTime.class).isEqualTo(rollEntity);
    }

    @Test
    public void testThatListRollsListsAllRolls() {
        RollEntity rollEntity = testDataUtil.prepareRollState();
        rollEntity.setId(null);
        rollEntity.setDiceRollOption(DiceRollOption.ONE);
        rollRepository.save(rollEntity);

        List<RollEntity> rolls = rollService.findAll();
        List<RollEntity> expectedRolls = List.of(rollEntity);
        then(rolls).isEqualTo(expectedRolls);
    }

    @Test
    public void testThatDeleteRollDeletesThatROll() {
        RollEntity rollEntity = testDataUtil.prepareRollState();
        rollEntity.setId(null);
        rollEntity.setDiceRollOption(DiceRollOption.ONE);
        RollEntity savedRoll = rollRepository.save(rollEntity);
        rollService.deleteById(savedRoll.getId());
        Optional<RollEntity> expectedRoll = rollService.findById(savedRoll.getId());
        then(expectedRoll).isEmpty();
    }

    @Test
    public void testThatEditRollEditsThatRoll() {
        RollEntity rollEntity = testDataUtil.prepareRollState();
        rollEntity.setId(null);
        rollEntity.setDiceRollOption(DiceRollOption.ONE);
        RollEntity savedRoll = rollRepository.save(rollEntity);

        savedRoll.setDiceRollOption(DiceRollOption.TWENTY);
        rollService.edit(savedRoll, savedRoll.getId());

        RollEntity expectedRoll = rollService.findById(savedRoll.getId()).get();
        then(expectedRoll.getDiceRollOption()).isEqualTo(DiceRollOption.TWENTY);
    }
}