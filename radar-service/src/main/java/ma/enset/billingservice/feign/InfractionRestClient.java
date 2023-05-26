package ma.enset.billingservice.feign;

import ma.enset.billingservice.model.Infraction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "infraction-service")
public interface InfractionRestClient {
    @GetMapping(path = "/infractions")
    Infraction infractions();
    @GetMapping(path = "/infractions/{id}")
    Infraction getProductById(@PathVariable("id") Long id);

    @PostMapping
    Infraction createInfraction(@RequestBody Infraction infraction);
}
