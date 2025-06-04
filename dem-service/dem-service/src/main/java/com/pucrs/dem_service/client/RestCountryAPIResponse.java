package com.pucrs.dem_service.client;

import java.util.List;
import java.util.Map;

public class RestCountryAPIResponse {

    public Name name;  
    public String ccn3;  
    public List<String> capital;
    public Long population;
    public Double area;
    public Map<String, CurrencyRaw> currencies;

    public static class Name {
        public String common;
    }

    public static class CurrencyRaw {
        public String name;
        public String symbol;
    }
}
