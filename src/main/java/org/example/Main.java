package org.example;

import org.example.httpClient.HttpClients;
import org.example.service.CountryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CountryService countryService = new CountryService();

        // Crear un objeto Scanner para leer la entrada del usuario

        System.out.println("OBTENIENDO DATA...");

        String answer = countryService.initGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Presiona Enter para continuar...");

        // Leer la entrada del usuario. El programa se pausará aquí hasta que se presione Enter.
        scanner.nextLine();
        System.out.println("La respuesta es: " + answer);


    }
}