package com.aleia.aleiaIactaEst.repositories;

import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {

}
