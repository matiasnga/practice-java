package org.example.httpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Country;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HttpExample {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://restcountries.com/v3.1/all"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            ObjectMapper mapper = new ObjectMapper();
            List<Country> countriesList = mapper.readValue(jsonResponse, new TypeReference<>() {});
            Predicate<Country> isFlagPresent = c-> c.getFlags().getAlt() != null;
            List<Country> filteredCountry = countriesList.stream().filter(isFlagPresent).collect(Collectors.toList());
            Random random = new Random();
            int randomIndex = random.nextInt(filteredCountry.size());
            Country randomCountry = filteredCountry.get(randomIndex);
            System.out.println(randomCountry);


        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
