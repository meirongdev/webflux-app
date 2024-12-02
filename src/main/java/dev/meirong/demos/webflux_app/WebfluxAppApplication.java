package dev.meirong.demos.webflux_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableCaching
public class WebfluxAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxAppApplication.class, args);
	}

}
