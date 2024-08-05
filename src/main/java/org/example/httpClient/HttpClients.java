package org.example.httpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.dto.Country;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HttpClients {

    private static final String COUNTRIES_API_URL = "https://restcountries.com/v3.1/all";

    public static List<Country> httpRequest() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COUNTRIES_API_URL))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            ObjectMapper mapper = new ObjectMapper();
            List<Country> countriesList = mapper.readValue(jsonResponse, new TypeReference<>() {
            });
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

    public static String httpRequestWikiExtract(String name) {
        try {
            // Construye la URL de la solicitud
            String encodedSearchTerm = URLEncoder.encode(name, StandardCharsets.UTF_8);
            String url = String.format("https://es.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=%s&inprop=url&explaintext=true", encodedSearchTerm.replace(" ", "_"));

            // Crea el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Envía la solicitud y obtiene la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Procesa la respuesta JSON
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject pages = jsonResponse.getAsJsonObject("query").getAsJsonObject("pages");
            // Extraer información de la página
            for (String key : pages.keySet()) {
                JsonObject page = pages.getAsJsonObject(key);
                String extract = page.get("extract").getAsString();
                // Encontrar la posición del primer punto
                int indexPunto = extract.indexOf('.');

                // Encontrar la posición de "00Ç" después del punto
                int index = extract.indexOf("==", indexPunto);

                if (indexPunto != -1 && index != -1) {
                    // Obtener el substring desde el primer punto hasta "00Ç"
                    String resultado = extract.substring(indexPunto + 1, index).trim();
                    System.out.println(resultado);
                        return resultado;
                } else {
                    System.out.println("No se encontró el punto o '00Ç' en el texto.");
                }
                return extract;
            }
        } catch (Exception e) {
            System.err.println("Error fetching wiki: " + e.getMessage());
        }
        return "";
    }


}
