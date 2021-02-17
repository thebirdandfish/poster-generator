package com.fermedu.poster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class PosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosterApplication.class, args);
	}

}
