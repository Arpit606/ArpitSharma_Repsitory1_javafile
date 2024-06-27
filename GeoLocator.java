import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeoLocator {
    private static class Country {
        String code;
        double latitude;
        double longitude;
        String countryFullName;

        Country(String code, double latitude, double longitude, String countryFullName) {
            this.code = code;
            this.latitude = latitude;
            this.longitude = longitude;
            this.countryFullName = countryFullName;
        }

        double distanceTo(double lat, double lon) {
            double latDiff = Math.toRadians(lat - this.latitude);
            double lonDiff = Math.toRadians(lon - this.longitude);
            double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                    + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(lat))
                            * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return 6371 * c; // Distance in kilometers (Earth's radius)
        }
    }

    public static void main(String[] args) {
        try {
            List<Country> countries = loadCountryCentroids("country_centroids.csv");

            // Example coordinates
            double[][] examples = {
                    { 23.338222, 78.953843 }, // India
                    { 64.226766, 101.052230 }, // Russia
                    { 35.228869, 100.981302 } // China
            };

            for (double[] example : examples) {
                double latitude = example[0];
                double longitude = example[1];
                String nearestCountry = findNearestCountry(latitude, longitude, countries);
                if (nearestCountry != null) {
                    System.out.println(
                            "Coordinates: (" + latitude + ", " + longitude + ") -> Country: " + nearestCountry);
                } else {
                    System.out.println("Coordinates: (" + latitude + ", " + longitude + ") -> Country not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Country> loadCountryCentroids(String csvFilePath) throws IOException {
        List<Country> countries = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line;
        // Skip the header line
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String code = parts[0];
            double latitude = Double.parseDouble(parts[1]);
            double longitude = Double.parseDouble(parts[2]);
            String countryFullName = parts[3];
            countries.add(new Country(code, latitude, longitude, countryFullName));
        }
        reader.close();
        return countries;
    }

    private static String findNearestCountry(double latitude, double longitude, List<Country> countries) {
        Country nearestCountry = null;
        double minDistance = Double.MAX_VALUE;

        for (Country country : countries) {
            double distance = country.distanceTo(latitude, longitude);
            // System.out.println("Distance from (" + latitude + ", " + longitude + ") to
            // country " + country.code +" ("+country.countryFullName+")"+" is " + distance
            // + " km"); // Debug line
            if (distance < minDistance) {
                minDistance = distance;
                nearestCountry = country;
            }
        }

        return nearestCountry != null ? nearestCountry.code + " (" + nearestCountry.countryFullName + ") " : null;
    }
}
