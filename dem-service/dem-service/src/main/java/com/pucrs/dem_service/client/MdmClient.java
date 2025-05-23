package com.pucrs.dem_service.client;


import com.pucrs.dem_service.dtos.CountryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MdmClient {

    private final RestTemplate restTemplate;

    @Autowired
    public MdmClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendCountry(CountryDTO countryDTO) {
        String mdmUrl = "http://localhost:8080/countries";

        restTemplate.postForObject(mdmUrl, countryDTO, Void.class);
    }
}




