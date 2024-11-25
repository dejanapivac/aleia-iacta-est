package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.services.impl.out.AddPlayersResponse;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.services.PartyService;
import com.aleia.aleiaIactaEst.services.PlayerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;

    private PlayerService playerService;

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
    public Optional<AddPlayersResponse> addPlayers(List<Integer> newPlayersIds, Integer partyId) {
        Optional<PartyEntity> partyEntity = partyRepository.findById(partyId);
        return partyEntity.map(party -> {
            List<PlayerEntity> newPlayers = playerService.findAllByIds(newPlayersIds);
            if(newPlayers.size() != newPlayersIds.size()) {
                return new AddPlayersResponse(party, "Not all players present");
            }
            party.getPlayers().addAll(newPlayers);
            partyRepository.save(party);
            return new AddPlayersResponse(party, "All players added");
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
        return partyRepository.findById(partyId).map(existingParty -> {
            existingParty.setName(party.getName());
            return partyRepository.save(existingParty);
        });
    }

    @Override
    public Optional<Integer> deleteById(Integer partyId) {
        Optional<PartyEntity> partyToDelete = partyRepository.findById(partyId);
        return partyToDelete.map(partyEntity -> {
            partyRepository.delete(partyEntity);
            return partyId;
        });
    }
}
