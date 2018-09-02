package aocampos.playlist.playlistweathersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PlaylistWeatherSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistWeatherSvcApplication.class, args);
	}
}
