package org.example.service;

import org.example.dto.Country;
import org.example.httpClient.HttpClients;
import java.util.List;
import java.util.Random;

public class CountryService {
    public String initGame() {
        List<Country> countryList = HttpClients.httpRequest();
        Random random = new Random();
        int index = random.nextInt(countryList.size());
        Country country = countryList.get(index);
        System.out.println(country.getFlag());
        System.out.println(country.getFlagDescription());
        System.out.println(DirectionCalculator.calculateDistance(country.getCapitalInfo().getLatitute()));
        String extract = HttpClients.httpRequestWikiExtract(country.getSpanishName());
        System.out.println(extract.replace(country.getSpanishName(), "******"));
        return country.getSpanishName();
    }
}
