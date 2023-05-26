package ma.enset.billingservice.web;

import ma.enset.billingservice.entities.DepassementVitesseRequest;
import ma.enset.billingservice.entities.Radar;
import ma.enset.billingservice.feign.ImmatriculationRestClient;
import ma.enset.billingservice.feign.InfractionRestClient;
import ma.enset.billingservice.model.Infraction;
import ma.enset.billingservice.model.Proprietaire;
import ma.enset.billingservice.model.Vehicule;
import ma.enset.billingservice.repository.RadarRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RadarRestController {
    @Autowired
    private RadarRespository radarRepository;
    @Autowired
    private ImmatriculationRestClient immatriculationClient;
    @Autowired
    private InfractionRestClient infractionClient;

    public RadarRestController(RadarRespository radarRepository, ImmatriculationRestClient immatriculationClient, InfractionRestClient infractionClient) {
        this.radarRepository = radarRepository;
        this.immatriculationClient = immatriculationClient;
        this.infractionClient = infractionClient;
    }

    @GetMapping("/{id}")
    public Radar getRadarById(@PathVariable Long id) {
        return radarRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Radar %s not found", id)));
    }
    @GetMapping("/vehicule/{id}")
    public Vehicule inf(@PathVariable Long id) {
        return immatriculationClient.vehicule(id) ;
    }
    @PostMapping("/depassement")
    public Infraction enregistrerDepassementDeVitesse(@RequestBody DepassementVitesseRequest request) {
        Radar r=radarRepository.findById(request.getRadarId());
        if(request.getVitesse() > r.getVitesseMaximale()){
            Vehicule vehicule = immatriculationClient.vehicule(request.getVehiculeID());
            Proprietaire proprietaire = immatriculationClient.owner(vehicule.getProprietaire().getId());

            // Créer une nouvelle infraction
            Infraction infraction = new Infraction();
            infraction.setDate(String.valueOf(new Date()));
            infraction.setRadarId(request.getRadarId());
            infraction.setVehicleID(request.getVehiculeID());
            infraction.setVehicleSpeed(request.getVitesse());
            infraction.setRadarMaxSpeed(request.getVitesseMaximale());
            infraction.setInfractionAmount(2000);
            return infractionClient.createInfraction(infraction);
        }
        return null;
    }


}
