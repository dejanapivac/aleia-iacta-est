package com.aleia.aleiaIactaEst.repositories;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends JpaRepository<PartyEntity, Integer> {
}
