package org.example.httpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Country;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HttpClient {

    private static final String COUNTRIES_API_URL = "https://restcountries.com/v3.1/all";

    public static List<Country> httpRequest() {
        try {
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COUNTRIES_API_URL))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            ObjectMapper mapper = new ObjectMapper();
            List<Country> countriesList = mapper.readValue(jsonResponse, new TypeReference<>() {});
            return filterCountriesWithFlags(countriesList);

        } catch (Exception e) {
            System.err.println("Error fetching countries: " + e.getMessage());
        }
        return List.of();
    }

    private static List<Country> filterCountriesWithFlags(List<Country> countries) {
        Predicate<Country> isFlagPresent = country -> country.getFlags().getAlt() != null;
        return countries.stream()
                .filter(isFlagPresent)
                .collect(Collectors.toList());
    }
}
