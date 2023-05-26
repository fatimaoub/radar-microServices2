package ma.enset.billingservice;

import ma.enset.billingservice.entities.Radar;
import ma.enset.billingservice.feign.ImmatriculationRestClient;
import ma.enset.billingservice.feign.InfractionRestClient;
import ma.enset.billingservice.repository.RadarRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RadarRespository radarRespository){
        return args -> {

            Radar radar =Radar.builder()
                    .vitesseMaximale(1000)
                    .longitude(20)
                    .latitude(40)
                    .build();
            radarRespository.save(radar);
            };
    }
}
