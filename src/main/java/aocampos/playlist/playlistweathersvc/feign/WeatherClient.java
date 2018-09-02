package aocampos.playlist.playlistweathersvc.feign;

import aocampos.playlist.playlistweathersvc.model.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherMap", url = "${open-weather-map.url}")
public interface WeatherClient {

    @GetMapping(value = "/weather")
    ResponseEntity<Weather> getWeatherByCityName(@RequestParam("q") String cityName,
                                                 @RequestParam("units") String unit,
                                                 @RequestParam("appid") String apiKey);

    @GetMapping(value = "/weather")
    ResponseEntity<Weather> getWeatherByCoordinates(@RequestParam("lat") double latitude,
                                                    @RequestParam("lon") double longitude,
                                                    @RequestParam("units") String unit,
                                                    @RequestParam("appid") String apiKey);
}
