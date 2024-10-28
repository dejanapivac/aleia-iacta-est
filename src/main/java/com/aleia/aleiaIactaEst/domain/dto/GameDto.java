package com.aleia.aleiaIactaEst.domain.dto;

import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {

    private Integer id;

    private String title;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime createdAt;

    private Integer partyDtoId;
}
