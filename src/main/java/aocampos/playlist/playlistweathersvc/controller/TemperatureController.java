package aocampos.playlist.playlistweathersvc.controller;

import aocampos.playlist.playlistweathersvc.model.Temperature;
import aocampos.playlist.playlistweathersvc.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping(value = "/cityName/{cityName}")
    public Temperature getTemperatureByCityName(@PathVariable("cityName") String cityName) {
        return temperatureService.getTemperatureByCityName(cityName);
    }

    @GetMapping(value = "/longitude/{lon}/latitude/{lat}")
    public Temperature getTemeratureByCoordinates(@PathVariable("lon") double longitude,
                                                  @PathVariable("lat") double latitude) {
        return temperatureService.getWeatherByCoordinates(longitude, latitude);
    }
}
