package com.aleia.aleiaIactaEst.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampaignDto {

    private Integer id;

    private String title;

    private Integer partyId;
}
