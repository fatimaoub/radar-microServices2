package ma.enset.immatriculationservice;

import ma.enset.immatriculationservice.entities.Owner;
import ma.enset.immatriculationservice.entities.Vehicle;
import ma.enset.immatriculationservice.web.rest.ImmatriculationRestController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ImmatriculationServiceApplication implements CommandLineRunner {

    private final ImmatriculationRestController immatriculationRestController;

    public ImmatriculationServiceApplication(ImmatriculationRestController immatriculationRestController) {
        this.immatriculationRestController = immatriculationRestController;
    }

    public static void main(String[] args) {
        SpringApplication.run(ImmatriculationServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Stream.of("Salma", "hamza", "mustapha").forEach(name -> {
            Owner owner = Owner.builder()
                    .name(name)
                    .birthDate(new Date().toString())
                    .email(name + "@gmail.com")
                    .build();
            immatriculationRestController.saveOwner(owner);
        });

        immatriculationRestController.getOwners().forEach(owner -> {
            Stream.of("renauls", "kia").forEach(brand -> {
                Vehicle vehicle = Vehicle.builder()
                        .regNumber("reg" + Math.random() * 10000)
                        .brand(brand)
                        .fiscalPower((float)(Math.random() * 10))
                        .model("modele " + Math.random() * 100)
                        .build();

                Long vehicleId = immatriculationRestController.saveVehicle(vehicle).getId();
                Long ownerId = owner.getId();

                immatriculationRestController.addVehicleToOwner(vehicleId, ownerId);
            });
        });
    }
}
