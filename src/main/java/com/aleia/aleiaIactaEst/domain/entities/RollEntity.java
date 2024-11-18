package com.aleia.aleiaIactaEst.domain.entities;

import com.aleia.aleiaIactaEst.domain.enums.DiceRollOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="rolls")
public class RollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    @Enumerated
    @Column(name = "roll_option", columnDefinition = "roll_option")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private DiceRollOption diceRollOption;
}
