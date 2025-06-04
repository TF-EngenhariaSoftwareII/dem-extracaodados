package com.pucrs.dem_service.services;

import org.springframework.stereotype.Service;

import com.pucrs.dem_service.client.CountriesClient;
import com.pucrs.dem_service.client.MdmClient;
import com.pucrs.dem_service.client.RestCountryAPIResponse;
import com.pucrs.dem_service.dtos.CountryDTO;
import com.pucrs.dem_service.dtos.CurrencyDTO;
import com.pucrs.dem_service.entities.ETLTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DemService {

    private final CountriesClient client;
    private final ETLTransactionService etlTransactionService;
    private final MdmClient mdmClient;  

    public DemService(CountriesClient client,
                      ETLTransactionService etlTransactionService,
                      MdmClient mdmClient) {
        this.client = client;
        this.etlTransactionService = etlTransactionService;
        this.mdmClient = mdmClient;
    }

    public List<CountryDTO> extractCountries() {
        ETLTransaction transaction = etlTransactionService.startTransaction();

        try {
            RestCountryAPIResponse[] apiCountries = client.fetchAllCountries();
            List<CountryDTO> countryDtos = new ArrayList<>();

            for (RestCountryAPIResponse apiCountry : apiCountries) {
                CountryDTO dto = convertToCountryDto(apiCountry);
                countryDtos.add(dto);

                
                mdmClient.sendCountry(dto);
            }

            etlTransactionService.finishTransactionSuccess(transaction.getId());
            return countryDtos;

        } catch (Exception e) {
            etlTransactionService.finishTransactionFailed(transaction.getId(), e.getMessage());
            throw new RuntimeException("ETL failed: " + e.getMessage(), e);
        }
    }

    private CountryDTO convertToCountryDto(RestCountryAPIResponse apiCountry) {
        CountryDTO dto = new CountryDTO();

        dto.setCountryName(apiCountry.name != null ? apiCountry.name.common : null);

        try {
            dto.setNumericCode(apiCountry.ccn3 != null ? Integer.parseInt(apiCountry.ccn3) : null);
        } catch (NumberFormatException e) {
            dto.setNumericCode(null);
        }

        dto.setCapitalCity((apiCountry.capital != null && !apiCountry.capital.isEmpty()) ? apiCountry.capital.get(0) : null);
        dto.setPopulation(apiCountry.population != null ? apiCountry.population.intValue() : null);
        dto.setArea(apiCountry.area);

        List<CurrencyDTO> currencyList = new ArrayList<>();
        if (apiCountry.currencies != null) {
            for (Map.Entry<String, RestCountryAPIResponse.CurrencyRaw> entry : apiCountry.currencies.entrySet()) {
                String code = entry.getKey();
                RestCountryAPIResponse.CurrencyRaw c = entry.getValue();
                currencyList.add(new CurrencyDTO(code, c.name, c.symbol));
            }
        }

        dto.setCurrencies(currencyList);
        return dto;
    }
}
