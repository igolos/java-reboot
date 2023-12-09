package ru.sberbank.edu;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.net.URI;


import java.time.LocalDateTime;


public class WeatherProvider {


    private static final String URL = "http://api.openweathermap.org";
    private String appKey;
    private RestTemplate restTemplate;



    public WeatherProvider(String appKey,RestTemplate restTemplate) {
        this.appKey=appKey;
        this.restTemplate=restTemplate;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        RestTemplate restTemplate = new RestTemplate();
        WeatherInfo weatherInfo = null;
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid="+appKey, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {

                String jsonResponse = responseEntity.getBody();
                ObjectMapper objectMapper = new ObjectMapper();


                JsonNode root = objectMapper.readTree(jsonResponse);

                String cityName = root.path("name").asText();
                String shortDescription = root.path("weather").get(0).path("main").asText();
                String description = root.path("weather").get(0).path("description").asText();
                double temperature = root.path("main").path("temp").asDouble();
                double feelsLikeTemperature = root.path("main").path("feels_like").asDouble();
                double windSpeed = root.path("wind").path("speed").asDouble();
                double pressure = root.path("main").path("pressure").asDouble();
                LocalDateTime expiryTime = LocalDateTime.now();

                weatherInfo = new WeatherInfo(cityName, shortDescription, description, temperature, feelsLikeTemperature, windSpeed, pressure, expiryTime);
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                System.out.println("City not found: " + city);
            } else {
                System.out.println("Unexpected HTTP status: " + e.getStatusCode().value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherInfo;
    }
}
