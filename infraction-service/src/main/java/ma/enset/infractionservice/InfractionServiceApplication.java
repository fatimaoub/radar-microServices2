package ma.enset.infractionservice;

import ma.enset.infractionservice.entities.Infraction;
import ma.enset.infractionservice.repositories.InfractionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class InfractionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfractionServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(InfractionRepository infractionRepository) {
		return args -> {
			for (int i=0; i<3;i++){
				Infraction infraction = Infraction.builder()
						.date("2023-05-18")
						.infractionAmount(i)
						.radarId((long) i)
						.build();
				infractionRepository.save(infraction);
			}
		};
}
}
