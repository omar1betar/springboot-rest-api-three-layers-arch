package net.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRestApiThreeLayersArchApplication {
	//use model mapper as spring beans
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringbootRestApiThreeLayersArchApplication.class, args);
	}

}
