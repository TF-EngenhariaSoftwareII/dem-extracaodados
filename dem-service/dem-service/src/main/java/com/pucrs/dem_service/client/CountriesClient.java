package com.pucrs.dem_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CountriesClient {

    private final String API_URL = "https://restcountries.com/v3.1/all";
    private final RestTemplate restTemplate = new RestTemplate();

    public RestCountryAPIResponse[] fetchAllCountries() {
        return restTemplate.getForObject(API_URL, RestCountryAPIResponse[].class);
    }
}

