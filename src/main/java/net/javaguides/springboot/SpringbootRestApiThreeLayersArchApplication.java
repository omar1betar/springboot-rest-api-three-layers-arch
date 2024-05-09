package net.javaguides.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Application Documentation",
				description = "Spring Boot Application Documentation",
				version = "1.0",
				contact = @Contact(
						name = "Omar Betar",
						email = "omar1betar@gmail.com",
						url = "www.google.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.google.com"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot user management ",
				url = "www.google.com"
		)
)
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
