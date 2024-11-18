package com.aleia.aleiaIactaEst.domain.dto;

import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RollDto {

    private Integer id;

    private Integer playerId;

    private Integer campaignId;

    private DiceRollOption diceRollOption;
}
