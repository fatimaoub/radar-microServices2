package ma.enset.billingservice.feign;

import ma.enset.billingservice.model.Proprietaire;
import ma.enset.billingservice.model.Vehicule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "immatriculation-service")
public interface ImmatriculationRestClient {
    @GetMapping(path = "/vehicules")
    List<Vehicule> vehicules();
    @GetMapping(path = "/vehicules/{id}")
    Vehicule vehicule(@PathVariable("id") Long id);
    @GetMapping(path = "/owners/{id}")
    Proprietaire owner(@PathVariable("id") Long id);
}
