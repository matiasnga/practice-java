package org.example.service;
public class DirectionCalculator {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convertir grados a radianes
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Diferencias
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Fórmula de Haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia en kilómetros
        return EARTH_RADIUS_KM * c;
    }

    public static String calculateDirection(double lat1, double lon1, double lat2, double lon2) {
        // Convertir grados a radianes
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Diferencias
        double deltaLon = lon2Rad - lon1Rad;

        // Calcular el ángulo
        double y = Math.sin(deltaLon) * Math.cos(lat2Rad);
        double x = Math.cos(lat1Rad) * Math.sin(lat2Rad)
                - Math.sin(lat1Rad) * Math.cos(lat2Rad) * Math.cos(deltaLon);
        double angle = Math.atan2(y, x);

        // Convertir ángulo de radianes a grados
        double angleDeg = Math.toDegrees(angle);
        angleDeg = (angleDeg + 360) % 360; // Normalizar el ángulo a [0, 360)

        // Determinar la dirección cardinal
        String[] directions = {"N", "NE", "E", "SE", "S", "SO", "O", "NO"};
        int index = (int) Math.round(angleDeg / 45) % 8;
        return directions[index];
    }

    public static void main(String[] args) {
        // Coordenadas de ejemplo
        double lat1 = -34.58; // Buenos Aires
        double lon1 = -58.67;
        double lat2 = -33.46; // Santiago
        double lon2 = -70.65;

        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        String direction = calculateDirection(lat1, lon1, lat2, lon2);

        System.out.printf("La respuesta está a %.2f km en dirección %s", distance, direction);
    }
}

