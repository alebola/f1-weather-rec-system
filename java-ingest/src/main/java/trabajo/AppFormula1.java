package trabajo;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AppFormula1 {
    public static void main(String[] args) throws IOException {

        List<Integer> years = Arrays.asList(2023, 2024);

        String sessions = Formula1Service.getSessions(years);
        JSONArray sessionsArray = new JSONArray(sessions);
        JSONArray allSessionsArray = FormulaDataPreprocessor.processSessionsData(sessionsArray);

        String drivers = Formula1Service.getDrivers(sessionsArray);
        JSONArray driversArray = new JSONArray(drivers);
        JSONArray allDriversArray = FormulaDataPreprocessor.processDriversData(driversArray);

        String stints = Formula1Service.getStints(sessionsArray);
        JSONArray stintsArray = new JSONArray(stints);
        JSONArray allStintsArray = FormulaDataPreprocessor.processStintsData(stintsArray);

        String weather = Formula1Service.getWeather(sessionsArray);
        JSONArray weatherArray = new JSONArray(weather);
        JSONArray allWeatherArray = FormulaDataPreprocessor.processWeatherData(weatherArray);

        String positions = Formula1Service.getPosition(sessionsArray);
        JSONArray positionsArray = new JSONArray(positions);
        JSONArray allPositionsArray = FormulaDataPreprocessor.processPositionData(positionsArray);

        try (MongoDBClient mongoDBClient = new MongoDBClient()) {
            mongoDBClient.insertSessionsData(allSessionsArray);
            mongoDBClient.insertDriversData(allDriversArray);
            mongoDBClient.insertStintsData(allStintsArray);
            mongoDBClient.insertWeatherData(allWeatherArray);
            mongoDBClient.insertPositionsData(allPositionsArray);
        }
    }
}
