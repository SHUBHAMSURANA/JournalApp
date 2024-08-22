package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Constants.PlaceHolder;
import net.engineeringdigest.journalApp.api.response.WeathertResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    //https://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=f2d2dc034ccb85a3e49f415bf9cb6d77

    @Value("${weather.api.key}")
    private String apiKey;
    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=<city>&appid=<apiKey>";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public  WeathertResponse getWeather(String city) {
        //String finalApi = API.replace(PlaceHolder.CITY,city).replace(PlaceHolder.API_KEY, apiKey);
        String finalApi = appCache.APP_CACHE.get("weather_api").replace(PlaceHolder.CITY,city).replace(PlaceHolder.API_KEY, apiKey);
        ResponseEntity<WeathertResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET,null, WeathertResponse.class);
        WeathertResponse body = response.getBody();
        return body;
    }
}
