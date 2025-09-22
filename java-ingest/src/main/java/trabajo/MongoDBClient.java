package trabajo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import org.json.JSONArray;

public class MongoDBClient implements AutoCloseable {
    private final MongoClient client;
    private final MongoDatabase database;

    public MongoDBClient() {
        String host = System.getenv().getOrDefault("MONGO_HOST", "localhost");
        int port = Integer.parseInt(System.getenv().getOrDefault("MONGO_PORT", "27017"));
        String dbName = System.getenv().getOrDefault("MONGO_DB", "F1-WeatherRec");

        this.client = new MongoClient(host, port);
        this.database = client.getDatabase(dbName);
    }

    public void insertSessionsData(JSONArray sessionsData) {
        MongoCollection<Document> col = database.getCollection("sessions");
        for (int i = 0; i < sessionsData.length(); i++) {
            col.insertOne(Document.parse(sessionsData.getJSONObject(i).toString()));
        }
    }

    public void insertDriversData(JSONArray driversData) {
        MongoCollection<Document> col = database.getCollection("drivers");
        for (int i = 0; i < driversData.length(); i++) {
            col.insertOne(Document.parse(driversData.getJSONObject(i).toString()));
        }
    }

    public void insertStintsData(JSONArray stintsData) {
        MongoCollection<Document> col = database.getCollection("stints");
        for (int i = 0; i < stintsData.length(); i++) {
            col.insertOne(Document.parse(stintsData.getJSONObject(i).toString()));
        }
    }

    public void insertWeatherData(JSONArray weatherData) {
        MongoCollection<Document> col = database.getCollection("weather");
        for (int i = 0; i < weatherData.length(); i++) {
            col.insertOne(Document.parse(weatherData.getJSONObject(i).toString()));
        }
    }

    public void insertPositionsData(JSONArray positionsData) {
        MongoCollection<Document> col = database.getCollection("positions");
        for (int i = 0; i < positionsData.length(); i++) {
            col.insertOne(Document.parse(positionsData.getJSONObject(i).toString()));
        }
    }

    @Override
    public void close() {
        client.close();
    }
}
