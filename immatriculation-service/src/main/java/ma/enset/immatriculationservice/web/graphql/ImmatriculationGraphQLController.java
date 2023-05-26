package ma.enset.immatriculationservice.web.graphql;

import ma.enset.immatriculationservice.entities.Owner;
import ma.enset.immatriculationservice.entities.Vehicle;
import ma.enset.immatriculationservice.repositories.OwnerRepository;
import ma.enset.immatriculationservice.repositories.VehicleRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ImmatriculationGraphQLController {
    private OwnerRepository ownerRepository;
    private VehicleRepository vehicleRepository;

    public ImmatriculationGraphQLController(VehicleRepository vehicleRepository, OwnerRepository ownerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepository;
    }

    //owner
    @QueryMapping
    public List<Owner> getOwners(){
        return ownerRepository.findAll();
    }


    @QueryMapping("getOwner")
    public Owner getOwnerById(@Argument Long id){
        return ownerRepository.findById(id).get();
    }


    @MutationMapping("saveOwner")
    public Owner saveOwner(@Argument Owner ownerRequest){
        Owner owner = new Owner();

        if(ownerRequest.getName() != null) owner.setName(ownerRequest.getName());
        if(ownerRequest.getEmail() != null) owner.setEmail(ownerRequest.getEmail());
        if(ownerRequest.getBirthDate() != null) owner.setBirthDate(ownerRequest.getBirthDate());

        owner.setId(null);
        owner.setVehicles(null);
        return ownerRepository.save(owner);
    }

    @MutationMapping("updateOwner")
    public Owner updateOwner(@Argument Long id, @Argument Owner ownerRequest){
        Owner owner = ownerRepository.findById(id).get();

        if(ownerRequest.getName() != null) owner.setName(ownerRequest.getName());
        if(ownerRequest.getEmail() != null) owner.setEmail(ownerRequest.getEmail());
        if(ownerRequest.getBirthDate() != null) owner.setBirthDate(ownerRequest.getBirthDate());

        return ownerRepository.save(owner);
    }

    @MutationMapping("deleteOwner")
    public boolean deleteOwner(@Argument Long id){
        ownerRepository.deleteById(id);
        return true;
    }
    //vehicule
    @QueryMapping
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    @QueryMapping("getVehicle")
    public Vehicle getVehicleById(@Argument Long id){
        return vehicleRepository.findById(id).get();
    }

    @MutationMapping("saveVehicle")
    public Vehicle saveVehicle(@Argument Vehicle vehicleRequest){
        Vehicle vehicle = new Vehicle();

        if(vehicleRequest.getRegNumber() != null) vehicle.setRegNumber(vehicleRequest.getRegNumber());
        if(vehicleRequest.getBrand() != null) vehicle.setBrand(vehicleRequest.getBrand());
        if(vehicleRequest.getFiscalPower() != null) vehicle.setFiscalPower(vehicleRequest.getFiscalPower());
        if(vehicleRequest.getModel() != null) vehicle.setModel(vehicleRequest.getModel());
        vehicle.setId(null);

        return vehicleRepository.save(vehicle);
    }

    @MutationMapping("updateVehicle")
    public Vehicle updateVehicle(@Argument Long id,@Argument Vehicle vehicleRequest){
        Vehicle vehicle = vehicleRepository.findById(id).get();

        if(vehicleRequest.getRegNumber() != null) vehicle.setRegNumber(vehicleRequest.getRegNumber());
        if(vehicleRequest.getBrand() != null) vehicle.setBrand(vehicleRequest.getBrand());
        if(vehicleRequest.getFiscalPower() != null) vehicle.setFiscalPower(vehicleRequest.getFiscalPower());
        if(vehicleRequest.getModel() != null) vehicle.setModel(vehicleRequest.getModel());
        if(vehicleRequest.getOwner() != null){
            Owner owner = new Owner();
            owner.setId(null);
            if (vehicleRequest.getOwner().getName() != null) owner.setName(vehicleRequest.getOwner().getName());
            if (vehicleRequest.getOwner().getEmail() != null) owner.setEmail(vehicleRequest.getOwner().getEmail());
            if (vehicleRequest.getOwner().getBirthDate() != null) owner.setBirthDate(vehicleRequest.getOwner().getBirthDate());
            vehicle.setOwner(owner);
        }

        return vehicleRepository.save(vehicle);
    }

    @MutationMapping("deleteVehicle")
    public boolean deleteVehicle(@Argument Long id){
        vehicleRepository.deleteById(id);
        return true;
    }

    @MutationMapping("addVehicleToOwner")
    public Vehicle addVehicleToOwner(@Argument Long ownerId, @Argument Long vehicleId){
        if(vehicleRepository.existsById(vehicleId) && ownerRepository.existsById(ownerId)){
            Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
            Owner owner = ownerRepository.findById(ownerId).get();
            vehicle.setOwner(owner);
            return vehicleRepository.save(vehicle);
        }
        else return null;
    }
}
