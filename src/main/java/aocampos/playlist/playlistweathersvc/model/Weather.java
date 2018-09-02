package aocampos.playlist.playlistweathersvc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    private int id;
    private int cod;
    private String name;
    private Main main;

    public double getTemperature() {
        return main.getTemp();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Main {
        private double temp;
        private int pressure;
        private int humidity;
        private int temp_min;
        private int temp_max;
    }
}
