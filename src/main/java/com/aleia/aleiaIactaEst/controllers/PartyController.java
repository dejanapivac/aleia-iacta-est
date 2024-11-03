package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.PartyService;
import com.aleia.aleiaIactaEst.services.PlayerService;
import jakarta.servlet.http.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/party")
public class PartyController {

    private PartyService partyService;

    private Mapper<PartyEntity, PartyDto> partyMapper;

    private Mapper<PlayerEntity, PlayerDto> playerMapper;

    public PartyController(PartyService partyService, Mapper<PartyEntity, PartyDto> partyMapper, Mapper<PlayerEntity, PlayerDto> playerMapper) {
        this.partyMapper = partyMapper;
        this.partyService = partyService;
        this.playerMapper = playerMapper;
    }

    @PostMapping
    public ResponseEntity<PartyDto> createParty(@RequestBody PartyDto party) {
        PartyEntity partyEntity = partyMapper.mapFrom(party);
        PartyEntity savedPartyEntity = partyService.save(partyEntity);
        return new ResponseEntity<>(partyMapper.mapTo(savedPartyEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PartyDto> listParties() {
        List<PartyEntity> parties = partyService.findAll();
        return parties.stream()
                .map(partyMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDto> getParty(@PathVariable("id") Integer id) {
        Optional<PartyEntity> foundParty = partyService.findOne(id);
        return foundParty.map(partyEntity -> {
            PartyDto partyDto = partyMapper.mapTo(partyEntity);
            return new ResponseEntity<>(partyDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/addPlayers")
    public ResponseEntity<PartyDto> addPlayers(@PathVariable("id") Integer partyId, @RequestBody Set<PlayerDto> playerDtos) {
        Set<PlayerEntity> playerEntities = playerDtos.stream().map(playerDto -> playerMapper.mapFrom(playerDto)).collect(Collectors.toSet());
        Optional<PartyEntity> updatedParty = partyService.addPlayers(playerEntities, partyId);
        return updatedParty.map(partyEntity -> {
            PartyDto updatedPartyDto = partyMapper.mapTo(partyEntity);
            return new ResponseEntity<>(updatedPartyDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/deletePlayers")
    public ResponseEntity<PartyDto> deletePlayers(@PathVariable("id") Integer partyId, @RequestBody Set<Integer> playerToDeleteIds) {
        Optional<PartyEntity> updatedParty = partyService.deletePlayers(playerToDeleteIds, partyId);
        return updatedParty.map(partyEntity -> {
            PartyDto updatedPartyDto = partyMapper.mapTo(partyEntity);
            return new ResponseEntity<>(updatedPartyDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartyDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody PartyDto partyDto) {
        PartyEntity partyEntity = partyMapper.mapFrom(partyDto);
        Optional<PartyEntity> savedPartyEntity = partyService.update(partyEntity, id);
        return savedPartyEntity.map(updatedParty -> {
            PartyDto updatedPartyDto = partyMapper.mapTo(updatedParty);
            return new ResponseEntity<>(updatedPartyDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteParty(@PathVariable("id") Integer partyId) {
        Optional<Integer> expectedPartyId = partyService.delete(partyId);
        return expectedPartyId.map(party -> {
            return new ResponseEntity<>(party, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
