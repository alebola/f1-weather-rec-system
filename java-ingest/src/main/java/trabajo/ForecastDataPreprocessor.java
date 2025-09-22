package trabajo;

import org.json.JSONArray;
import org.json.JSONObject;

public class ForecastDataPreprocessor {

    public JSONArray processForecastData(String rawJson) {
        JSONObject json = new JSONObject(rawJson);
        JSONArray list = json.getJSONArray("list");
        JSONObject cityInfo = json.getJSONObject("city");
        String cityName = cityInfo.getString("name");
        JSONArray processedList = new JSONArray();

        for (int i = 0; i < list.length(); i++) {
            JSONObject forecast = list.getJSONObject(i);
            String dt_txt = forecast.getString("dt_txt"); // Fecha y hora del pronóstico

            // Filtrar para incluir solo entradas a las 12:00 PM
            if (dt_txt.endsWith("12:00:00")) {
                JSONObject main = forecast.getJSONObject("main");
                JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);
                JSONObject wind = forecast.getJSONObject("wind");

                String description = weather.getString("description");
                double temp = main.getDouble("temp");
                double windSpeed = wind.getDouble("speed");

                JSONObject out = new JSONObject()
                        .put("city", cityName)
                        .put("datetime", dt_txt)
                        .put("description", description)
                        .put("temperature", new JSONObject()
                                .put("current", temp)
                                .put("windSpeed", windSpeed));

                processedList.put(out);
            }
        }

        return processedList;
    }
}
