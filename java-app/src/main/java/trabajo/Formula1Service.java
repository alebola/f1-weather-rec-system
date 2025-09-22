package trabajo;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class Formula1Service {
    private static final String URL_TEMPLATE = "https://api.openf1.org/v1/";

    public static String getSessions(List<Integer> years) throws IOException {
        JSONArray sessions = new JSONArray();
        for (int year : years) {
            String response = makeRequest("sessions?year=" + year + "&session_name=Race");
            JSONArray sessionsArray = new JSONArray(response);
            for (int i = 0; i < sessionsArray.length(); i++) {
                sessions.put(sessionsArray.getJSONObject(i));
            }
        }
        return sessions.toString();
    }
    public static String getDrivers(JSONArray sessionArray) throws IOException {
        JSONArray drivers = new JSONArray();
        for (int i = 0; i < sessionArray.length(); i++) {
            int session_key = sessionArray.getJSONObject(i).getInt("session_key");
            String response = makeRequest("drivers?session_key=" + session_key);
            JSONArray driversArray = new JSONArray(response);
            for(int j = 0; j < driversArray.length(); j++) {
                drivers.put(driversArray.getJSONObject(j));
            }
        }
        return drivers.toString();
    }

    public static String getStints(JSONArray sessionsArray) throws IOException {
        JSONArray stints = new JSONArray();
        for (int i = 0; i < sessionsArray.length(); i++) {
            int session_key = sessionsArray.getJSONObject(i).getInt("session_key");
            String response = makeRequest("stints?session_key=" + session_key);
            JSONArray stintsArray = new JSONArray(response);
            for (int j = 0; j < stintsArray.length(); j++) {
                stints.put(stintsArray.getJSONObject(j));
            }
        }
        return stints.toString();
    }

    public static String getPosition(JSONArray sessionsArray) throws IOException {
        JSONArray positions = new JSONArray();
        for (int i = 0; i < sessionsArray.length(); i++) {
            int session_key = sessionsArray.getJSONObject(i).getInt("session_key");
            String response = makeRequest("position?session_key=" + session_key);
            JSONArray positionsArray = new JSONArray(response);
            for (int j = 0; j < positionsArray.length(); j++) {
                positions.put(positionsArray.getJSONObject(j));
            }
        }
        return positions.toString();
    }

    public static String getWeather(JSONArray sessionsArray) throws IOException {
        JSONArray weather = new JSONArray();
        for (int i = 0; i < sessionsArray.length(); i++) {
            int session_key = sessionsArray.getJSONObject(i).getInt("session_key");
            String response = makeRequest("weather?session_key=" + session_key);
            JSONArray weatherArray = new JSONArray(response);
            for (int j = 0; j < weatherArray.length(); j++) {
                weather.put(weatherArray.getJSONObject(j));
            }
        }
        return weather.toString();
    }

    private static String makeRequest(String request) throws IOException {
        String url = String.format(URL_TEMPLATE + request);
        Response response = Request.Get(url).execute();
        HttpResponse httpResponse = response.returnResponse();
        String responseString = EntityUtils.toString(httpResponse.getEntity());
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 429) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return makeRequest(request);
        }
        return responseString;
    }

}
