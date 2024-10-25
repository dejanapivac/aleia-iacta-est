package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.PartyService;
import com.aleia.aleiaIactaEst.services.PlayerService;
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

    public PartyController(PartyService partyService, Mapper<PartyEntity, PartyDto> partyMapper) {
        this.partyMapper = partyMapper;
        this.partyService = partyService;
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

    @PutMapping("/{id}")
    public ResponseEntity<PartyDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody PartyDto partyDto) {

        if(!partyService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PartyEntity partyEntity = partyMapper.mapFrom(partyDto);
        PartyEntity savedPartyEntity = partyService.save(partyEntity);
        return new ResponseEntity<>(
                partyMapper.mapTo(savedPartyEntity),
                HttpStatus.OK
        );
    }
}
