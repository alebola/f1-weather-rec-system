// WeatherService.java
package trabajo;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public class WeatherService {
    private static final String URL_TEMPLATE =
            "https://api.openweathermap.org/data/2.5/forecast?q=%s&APPID=%s&units=metric";

    public static String getWeatherData(String city, String countryCode) throws Exception {
        String apiKey = System.getenv("OPENWEATHER_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OPENWEATHER_API_KEY env var is not set.");
        }
        String url = String.format(URL_TEMPLATE, city + "," + countryCode, apiKey);
        Response response = Request.Get(url).execute();
        return response.returnContent().asString();
    }
}
