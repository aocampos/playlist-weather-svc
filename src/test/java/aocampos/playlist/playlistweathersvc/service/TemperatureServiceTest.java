package aocampos.playlist.playlistweathersvc.service;

import aocampos.playlist.playlistweathersvc.feign.WeatherClient;
import aocampos.playlist.playlistweathersvc.model.Temperature;
import aocampos.playlist.playlistweathersvc.model.Weather;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceTest {

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private TemperatureService temperatureService;

    private Weather weather;

    private String apiKey;

    private String unit;

    @Before
    public void setup() {
        apiKey = "123456";
        unit = "metric";
        ReflectionTestUtils.setField(temperatureService, "openWeatherMapApiKey", apiKey);
        ReflectionTestUtils.setField(temperatureService, "openWeatherMapUnit", unit);

        weather = new Weather();
        weather.setId(2643743);
        weather.setCod(200);
        weather.setName("London");

        Weather.Main main = new Weather().new Main();
        main.setTemp(12.66);
        main.setPressure(1026);
        main.setHumidity(77);
        main.setTemp_min(10);
        main.setTemp_max(15);

        weather.setMain(main);
    }

    @Test
    public void testGetTemperatureByCityNameSuccessfully() {
        String cityName = "London";

        when(weatherClient.getWeatherByCityName(cityName, unit, apiKey)).thenReturn(ResponseEntity.ok(weather));

        Temperature temperature = temperatureService.getTemperatureByCityName(cityName);

        verify(weatherClient, times(1)).getWeatherByCityName(cityName, unit, apiKey);

        assertEquals(weather.getTemperature(), temperature.getTemperature(), 0.0);
    }

    @Test
    public void testGetWeatherByCoordinatesSuccessfully() {
        double longitude = -0.13;
        double latitude = 51.51;

        when(weatherClient.getWeatherByCoordinates(longitude, latitude, unit, apiKey)).thenReturn(ResponseEntity.ok(weather));

        Temperature temperature = temperatureService.getWeatherByCoordinates(longitude, latitude);

        verify(weatherClient, times(1)).getWeatherByCoordinates(longitude, latitude, unit, apiKey);

        assertEquals(weather.getTemperature(), temperature.getTemperature(), 0.0);
    }
}