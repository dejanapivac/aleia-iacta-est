package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.RollDto;
import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.RollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/rolls")
public class RollController {

    private RollService rollService;

    private Mapper<RollEntity, RollDto> rollMapper;

    public RollController(RollService rollService, Mapper<RollEntity, RollDto> rollMapper) {
        this.rollService = rollService;
        this.rollMapper = rollMapper;
    }

    @PostMapping
    public ResponseEntity<RollDto> createARoll(@RequestBody RollDto rollDto) {
        RollEntity rollEntity = rollMapper.mapFrom(rollDto);
        RollEntity savedRollEntity = rollService.save(rollEntity);
        return new ResponseEntity<>(rollMapper.mapTo(savedRollEntity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RollDto> getRoll(@PathVariable("id") Integer id) {
        Optional<RollEntity> rollEntity = rollService.findById(id);
        return rollEntity.map(expectedRoll ->{
            RollDto rollDto = rollMapper.mapTo(expectedRoll);
            return new ResponseEntity<>(rollDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<RollDto> listRolls() {
        List<RollEntity> rollEntities = rollService.findAll();
        return rollEntities.stream()
                .map(rollEntity -> rollMapper.mapTo(rollEntity))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public Optional<Integer> deleteRoll(@PathVariable("id") Integer id) {
        Optional<RollEntity> expectedRoll = rollService.findById(id);
        return expectedRoll.map(rollEntity -> {
            Integer rollEntityId = rollEntity.getId();
            rollService.deleteById(rollEntityId);
            return id;
        });
    }
}
