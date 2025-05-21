package com.pucrs.dem_service.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.dem_service.dtos.CountryDTO;
import com.pucrs.dem_service.services.DemService;

import java.util.List;

@RestController
public class DemController {

    private final DemService service;

    public DemController(DemService service) {
        this.service = service;
    }

    @GetMapping("/etl/start")
    public List<CountryDTO> startExtraction() {
        return service.extractCountries();
    }
}
