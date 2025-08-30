package ge.tbc.testautomation.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class MapUtil {

    public static JSONObject getAddressFromCoordinates(String lat, String lon) {
        try {
            String urlStr = String.format(
                    "https://nominatim.openstreetmap.org/reverse?lat=%s&lon=%s&format=json&accept-language=en",
                    lat, lon);
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "TestAutomationBot"); // required by nominatim

            String response = new Scanner(con.getInputStream())
                    .useDelimiter("\\A")
                    .next();

            JSONObject obj = new JSONObject(response);
            return obj.getJSONObject("address");

        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject(); // return empty object if error
        }
    }

    public static boolean isCoordinateInExpectedCity(String coordinates, String expectedCity) {
        String[] parts = coordinates.split(",");
        String lat = parts[0];
        String lon = parts[1];
        JSONObject address = getAddressFromCoordinates(lat, lon);
        String actualCity = address.optString("city", "");
        return actualCity.equalsIgnoreCase(expectedCity);
    }

    public static String getRoad(String coordinates) {
        String[] parts = coordinates.split(",");
        String lat = parts[0];
        String lon = parts[1];
        JSONObject address = getAddressFromCoordinates(lat, lon);
        return address.optString("road", "");
    }


}