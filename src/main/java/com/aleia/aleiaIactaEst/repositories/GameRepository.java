package com.aleia.aleiaIactaEst.repositories;

import com.aleia.aleiaIactaEst.domain.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends JpaRepository<GameEntity, Integer> {
}
