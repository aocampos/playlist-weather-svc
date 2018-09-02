package aocampos.playlist.playlistweathersvc.service;

import aocampos.playlist.playlistweathersvc.feign.WeatherClient;
import aocampos.playlist.playlistweathersvc.model.Temperature;
import aocampos.playlist.playlistweathersvc.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {

    @Value("${open-weather-map.api-key}")
    private String openWeatherMapApiKey;

    @Value("${open-weather-map.unit}")
    private String openWeatherMapUnit;

    private final WeatherClient weatherClient;

    @Autowired
    public TemperatureService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Temperature getTemperatureByCityName(String cityName) {
        ResponseEntity<Weather> weatherResponse = weatherClient.getWeatherByCityName(cityName, openWeatherMapUnit, openWeatherMapApiKey);
        Weather weather = weatherResponse.getBody();
        return this.getTemperature(weather);
    }

    public Temperature getWeatherByCoordinates(double longitude, double latitude) {
        ResponseEntity<Weather> weatherResponse = weatherClient.getWeatherByCoordinates(longitude, latitude, openWeatherMapUnit, openWeatherMapApiKey);
        Weather weather = weatherResponse.getBody();
        return this.getTemperature(weather);
    }

    private Temperature getTemperature(Weather weather) {
        Temperature temperature = new Temperature();
        temperature.setTemperature(weather.getTemperature());
        return temperature;
    }
}
