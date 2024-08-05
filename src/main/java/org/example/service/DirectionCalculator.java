package org.example.service;

import java.text.DecimalFormat;
import java.util.List;

public class DirectionCalculator {

    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double LAT_BUENOS_AIRES = -34.58;
    private static final double LON_BUENOS_AIRES = -58.67;

    public static String calculateDistance(List<Double> destination) {
        // Convertir grados a radianes
        double lat1Rad = Math.toRadians(LAT_BUENOS_AIRES);
        double lon1Rad = Math.toRadians(LON_BUENOS_AIRES);
        double lat2Rad = Math.toRadians(destination.get(0));
        double lon2Rad = Math.toRadians(destination.get(1));

        // Diferencias
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Fórmula de Haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        double y = Math.sin(deltaLon) * Math.cos(lat2Rad);
        double x = Math.cos(lat1Rad) * Math.sin(lat2Rad)
                - Math.sin(lat1Rad) * Math.cos(lat2Rad) * Math.cos(deltaLon);
        double angle = Math.atan2(y, x);

        double angleDeg = Math.toDegrees(angle);
        angleDeg = (angleDeg + 360) % 360; // Normalizar el ángulo a [0, 360)

        String[] directions = {"N", "NE", "E", "SE", "S", "SO", "O", "NO"};
        int index = (int) Math.round(angleDeg / 45) % 8;

        String direction = directions[index];
        String pattern = "###,###,###.## km";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(distance);

        return "La respuesta está a " + output + " en dirección " +  direction;
    }
}

