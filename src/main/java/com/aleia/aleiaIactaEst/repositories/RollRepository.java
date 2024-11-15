package com.aleia.aleiaIactaEst.repositories;

import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RollRepository extends JpaRepository<RollEntity, Integer> {
}
