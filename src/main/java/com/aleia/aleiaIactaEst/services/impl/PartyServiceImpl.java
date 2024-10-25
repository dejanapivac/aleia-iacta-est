package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.services.PartyService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;

    public PartyServiceImpl(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;

    }

    @Override
    public PartyEntity save(PartyEntity partyEntity) {
        return partyRepository.save(partyEntity);
    }

    @Override
    public List<PartyEntity> findAll() {
        return StreamSupport.stream(partyRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PartyEntity> findOne(Integer id) {
        return partyRepository.findById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return partyRepository.existsById(id);
    }

    @Override
    public Optional<PartyEntity> addPlayers(Set<PlayerEntity> newPlayersSet, Integer partyId) {
//        dohvati mi taj party
//        ako postoji dodaj novog playera
//        save party
        Optional<PartyEntity> partyEntity = partyRepository.findById(partyId);
        return partyEntity.map(party -> {
            party.getPlayers().addAll(newPlayersSet);
            return partyRepository.save(party);
        });
    }

    @Override
    public Optional<PartyEntity> deletePlayers(Set<Integer> playersToDelete, Integer partyId) {
        Optional<PartyEntity> partyEntity = partyRepository.findById(partyId);
        return partyEntity.map(party -> {
            Set<PlayerEntity> players = party.getPlayers();
            players.removeIf(player -> playersToDelete.contains(player.getId()));
            return partyRepository.save(party);
        });
    }

    @Override
    public Optional<PartyEntity> update(PartyEntity party, Integer partyId) {
        return partyRepository.findById(partyId)
                .map(existingParty -> partyRepository.save(party));
    }
}
