package ma.enset.infractionservice.web;

import ma.enset.infractionservice.entities.Infraction;
import ma.enset.infractionservice.repositories.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InfractionGraphQlController {
    @Autowired
    private final InfractionRepository infractionRepository;

    public InfractionGraphQlController(InfractionRepository infractionRepository) {
        this.infractionRepository = infractionRepository;
    }
    @QueryMapping
    public List<Infraction> allInfractions() {
        return infractionRepository.findAll();
    }
    @QueryMapping
    public Infraction infractionById(Long id) {
        return infractionRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Infraction %s not found", id)));
    }
    @MutationMapping
    public Infraction createInfraction(String date, Long radarId, String vehicleMatricule,
                                       int vehicleSpeed, int radarMaxSpeed, double infractionAmount) {
        Infraction infraction = new Infraction(date, radarId, vehicleMatricule, vehicleSpeed, radarMaxSpeed, infractionAmount);
        return infractionRepository.save(infraction);
    }
    @MutationMapping
    public Infraction updateInfraction(Long id, String date, Long radarId, String vehicleMatricule,
                                       int vehicleSpeed, int radarMaxSpeed, double infractionAmount) {
        Infraction infraction = infractionRepository.findById(id).orElse(null);
        if (infraction != null) {
            infraction.setDate(date);
            infraction.setRadarId(radarId);
            infraction.setVehicleID(Long.valueOf(vehicleMatricule));
            infraction.setVehicleSpeed(vehicleSpeed);
            infraction.setRadarMaxSpeed(radarMaxSpeed);
            infraction.setInfractionAmount(infractionAmount);
            return infractionRepository.save(infraction);
        } else {
            return null;
        }
    }
    @MutationMapping
    public boolean deleteInfraction(Long id) {
        Infraction infraction = infractionRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Infraction %s not found", id)));
        if (infraction != null) {
            infractionRepository.delete(infraction);
            return true;
        } else {
            return false;
        }
    }
}
