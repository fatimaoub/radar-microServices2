package ma.enset.infractionservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import ma.enset.infractionservice.entities.Infraction;
import ma.enset.infractionservice.repositories.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(serviceName = "InfractionWS")
public class InfractionSoapController {
    private final InfractionRepository infractionRepository;

    @Autowired
    public InfractionSoapController(InfractionRepository infractionRepository) {
        this.infractionRepository = infractionRepository;
    }

    @WebMethod(operationName = "getInfractionById")
    public Infraction getInfractionById(@WebParam(name = "id") Long id) {
        return infractionRepository.findById(id).orElse(null);
    }

    @WebMethod(operationName = "getAllInfractions")
    public List<Infraction> getAllInfractions() {
        return infractionRepository.findAll();
    }

    @WebMethod(operationName = "createInfraction")
    public Infraction createInfraction(@WebParam(name = "infraction") Infraction infraction) {
        return infractionRepository.save(infraction);
    }

    @WebMethod(operationName = "updateInfraction")
    public Infraction updateInfraction(@WebParam(name = "infraction") Infraction infraction) {
        return infractionRepository.save(infraction);
    }

    @WebMethod(operationName = "deleteInfraction")
    public boolean deleteInfraction(@WebParam(name = "id") Long id) {
        Infraction infraction = infractionRepository.findById(id).orElse(null);
        if (infraction != null) {
            infractionRepository.delete(infraction);
            return true;
        } else {
            return false;
        }
    }
}
