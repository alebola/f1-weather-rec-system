# F1-Weather-Rec-System  

🚦 **F1-Weather-Rec-System** is a project that combines **Java, Python, Flask, PyQt, and MongoDB** to analyze Formula 1 race data together with weather information.  
The system integrates **external APIs** to collect raw data, processes it, and allows running advanced queries through an API and a desktop GUI.  


## 📌 Tech Stack
- **Java + Maven** → Data ingestion and preprocessing from external APIs.  
- **Python (Flask + PyQt)** → REST API backend and desktop client.  
- **MongoDB** → NoSQL database to store sessions, drivers, positions, stints, and weather data.  
- **PyMongo** → Python driver for MongoDB.  
- **PyQt5** → GUI for executing queries.  
- **Requests** → API consumption from the client.  


## 🌐 External APIs
The system integrates real-world data using:  
- **Formula 1 Data API** → race results, drivers, sessions, and stints.  
- **OpenWeather API** → weather conditions (rain, humidity, track/air temperature, wind speed).    


## 📂 Repository structure
```bash
f1-weather-rec-system/
├── java-app/
│   ├── src/main/java/trabajo/   # Java classes for API integration and preprocessing
│   └── pom.xml                  # Maven configuration
│
├── python-app/
│   ├── server.py                # Flask API with MongoDB queries
│   ├── client.py                # PyQt desktop client
│   └── requirements.txt         # Python dependencies
│
├── README.md
```


## ⚙️ Setup and Usage
🔹 Prerequisites
- **Java 17+** and **Maven 3+**
- **Python 3.10+**
- **MongoDB** running locally (mongodb://localhost:27017/)
- API keys for:
  - **Formula 1 Data API**
  - **OpenWeather API**
    
👉 Store your API keys as **environment variables** before running the apps:
```bash
export F1_API_KEY=your_api_key_here
export WEATHER_API_KEY=your_api_key_here
```

#### 🔹 Java Backend
1. Navigate to the Java app:
```bash
cd f1-weather-rec-system/java-app
```
2. Build with Maven:
```bash
mvn clean package
```
3. Run:
```bash
java -jar target/F1-WeatherRec.jar
```
This will fetch and preprocess data from external APIs, storing it into **MongoDB**.

#### 🔹 Flask API (Python)
1. Navigate to the Python app:
```bash
cd f1-weather-rec-system/python-app
```
2. Install dependencies:
```bash
pip install -r requirements.txt
```
3. Start the Flask server:
```bash
python server.py
```
The API will be available at `http://127.0.0.1:5000`.

#### 🔹 PyQt Client (Python)
1. With the API running, start the client:
```bash
python client.py
```
2. A GUI will open with a **query selector**:
   - **Query 1**: Top 3 drivers per circuit.
   - **Query 2**: Race winners in rainy sessions and tire usage.
   - **Query 3**: Winner details with average weather conditions.
  

## 📊 Key Features
✅ Collect and preprocess **Formula 1 race data** and **weather data** from external APIs.
✅ Store enriched information in **MongoDB**.
✅ Expose insights through a **Flask REST API**.
✅ Execute queries via a **PyQt5 desktop GUI**.
✅ Data preprocessing and services implemented in **Java**.


## 🚀 Project Value
This project demonstrates strong skills in:
- **Multi-language integration (Java + Python)**.
- **External API integration (F1 API + OpenWeather)**.
- **REST API design and consumption**.
- **NoSQL databases (MongoDB)** in analytics contexts.
- **Desktop GUI development with PyQt**.
It represents a real-world Data-Driven Application, showcasing how to combine sports performance with external weather data to extract insights.
