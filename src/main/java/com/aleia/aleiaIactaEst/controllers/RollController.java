package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.RollDto;
import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.RollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
