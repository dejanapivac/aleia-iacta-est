package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.CampaignDto;
import com.aleia.aleiaIactaEst.domain.entities.CampaignEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/campaigns")
public class CampaignController {

    private CampaignService campaignService;


    private Mapper<CampaignEntity, CampaignDto> campaignMapper;

    public CampaignController(CampaignService campaignService, Mapper<CampaignEntity, CampaignDto> campaignMapper) {
        this.campaignService = campaignService;
        this.campaignMapper = campaignMapper;
    }

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto) {
        CampaignEntity campaignEntity = campaignMapper.mapFrom(campaignDto);
        CampaignEntity savedCampaignEntity = campaignService.save(campaignEntity);
        return new ResponseEntity<>(campaignMapper.mapTo(savedCampaignEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CampaignDto> listCampaigns() {
        List<CampaignEntity> campaignEntities = campaignService.list();
        return campaignEntities.stream()
                .map(campaignEntity -> campaignMapper.mapTo(campaignEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable("id") Integer id) {
        Optional<CampaignEntity> expectedCampaign = campaignService.findById(id);
        return expectedCampaign.map(campaignEntity -> {
            CampaignDto campaignDto = campaignMapper.mapTo(campaignEntity);
            return new ResponseEntity<>(campaignDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CampaignDto> updateCampaign(@PathVariable("id") Integer id, @RequestBody CampaignDto campaignDto) {
        CampaignEntity campaignEntity = campaignMapper.mapFrom(campaignDto);
        Optional<CampaignEntity> expectedCampaign = campaignService.update(campaignEntity, id);
        return expectedCampaign.map(campaign -> {
            CampaignDto updateCampaignDto = campaignMapper.mapTo(campaign);
            return new ResponseEntity<>(updateCampaignDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Optional<Integer> deleteCampaign(@PathVariable("id") Integer id) {
        Optional<CampaignEntity> campaignEntity = campaignService.findById(id);
        return campaignEntity.map(expectedCampaign -> {
            Integer expectedCampaignId = expectedCampaign.getId();
            campaignService.deleteById(expectedCampaignId);
            return id;
        });
    }
}
