//package com.aleia.aleiaIactaEst.services;
//
//import com.aleia.aleiaIactaEst.IntegrationTestBase;
//import com.aleia.aleiaIactaEst.TestDataUtil;
//import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
//import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
//import com.aleia.aleiaIactaEst.repositories.RollRepository;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//
//@RequiredArgsConstructor
//public class StatisticsServiceTests extends IntegrationTestBase {
//
//    private final TestDataUtil testDataUtil;
//
//    private final RollService rollService;
//
//    private final RollRepository rollRepository;
//
//    @Test
//    public void testThatGetStatisticsForAPlayerInCampaignGetsThatStatistics() {
//        RollEntity rollEntity = testDataUtil.prepareRollState();
//        rollEntity.setId(null);
//        rollEntity.setDiceRollOption(DiceRollOption.ONE);
//        rollService.save(rollEntity);
//        rollEntity.setDiceRollOption(DiceRollOption.ONE);
//        rollService.save(rollEntity);
//        rollEntity.setDiceRollOption(DiceRollOption.TWENTY);
//        rollService.save(rollEntity);
//
//        RollEntity rollEntityB = testDataUtil.prepareRollState();
//        rollEntityB.setId(null);
//        rollEntityB.setSession();
//        rollEntityB.setPlayer(rollEntityB.getPlayer());
//    }
//}
