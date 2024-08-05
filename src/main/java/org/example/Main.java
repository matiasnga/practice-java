package org.example;

import org.example.dto.Country;
import org.example.httpClient.HttpClient;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Country> countryList = HttpClient.httpRequest();
        System.out.println(countryList);
    }
}