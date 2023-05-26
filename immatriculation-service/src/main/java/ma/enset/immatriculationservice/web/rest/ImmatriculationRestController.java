package ma.enset.immatriculationservice.web.rest;
import ma.enset.immatriculationservice.entities.Owner;
import ma.enset.immatriculationservice.entities.Vehicle;
import ma.enset.immatriculationservice.repositories.OwnerRepository;
import ma.enset.immatriculationservice.repositories.VehicleRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/web")
public class ImmatriculationRestController {
    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;

    public ImmatriculationRestController(VehicleRepository vehicleRepository, OwnerRepository ownerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepository;
    }
   //owner
    @GetMapping("/owners")
    public List<Owner> getOwners(){
        return ownerRepository.findAll();
    }


    @GetMapping("/owners/{id}")
    public Owner getOwnerById(@PathVariable("id") Long id){
        return ownerRepository.findById(id).get();
    }

    @PostMapping("/owners")
    public Owner saveOwner(@RequestBody Owner ownerRequest){
        Owner owner = Owner.builder()
                .id(null)
                .name(ownerRequest.getName())
                .email(ownerRequest.getEmail())
                .birthDate(ownerRequest.getBirthDate())
                .build();

        return ownerRepository.save(owner);
    }

    @PutMapping("/owners/{id}")
    public Owner updateOwner(@PathVariable("id") Long id,@RequestBody Owner ownerRequest){
        if(ownerRepository.existsById(id)){
            Owner owner = ownerRepository.findById(id).get();

            if(ownerRequest.getName() != null) owner.setName(ownerRequest.getName());
            if(ownerRequest.getEmail() != null) owner.setEmail(ownerRequest.getEmail());
            if(ownerRequest.getBirthDate() != null) owner.setBirthDate(ownerRequest.getBirthDate());

            return ownerRepository.save(owner);
        }
        else return null;
    }

    @DeleteMapping("/owners/{id}")
    public boolean deleteOwner(@PathVariable("id") Long id){
        if(ownerRepository.existsById(id)){
            ownerRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    @GetMapping("/owners/exist/{id}")
    public boolean ownerExists(@PathVariable("id") Long id){
        return ownerRepository.existsById(id);
    }

    //vehicule

    @GetMapping("/vehicles")
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle getVehicleById(@PathVariable("id") Long id){
        if(vehicleRepository.existsById(id)){
            return vehicleRepository.findById(id).get();
        }
        else return null;
    }

    @PostMapping("/vehicles")
    public Vehicle saveVehicle(@RequestBody Vehicle vehicleRequest){
        Vehicle vehicle = Vehicle.builder()
                .id(null)
                .owner(null)
                .brand(vehicleRequest.getBrand())
                .fiscalPower(vehicleRequest.getFiscalPower())
                .model(vehicleRequest.getModel())
                .regNumber(vehicleRequest.getRegNumber())
                .build();
        return vehicleRepository.save(vehicle);
    }

    @PutMapping("/vehicles/{id}")
    public Vehicle updateVehicle(@PathVariable("id") Long id,@RequestBody Vehicle vehicleRequest){
        if(vehicleRepository.existsById(id)){
            Vehicle vehicle = vehicleRepository.findById(id).get();
            if (vehicleRequest.getBrand() != null) vehicle.setBrand(vehicleRequest.getBrand());
            if (vehicleRequest.getFiscalPower() != null) vehicle.setFiscalPower(vehicleRequest.getFiscalPower());
            if (vehicleRequest.getModel() != null) vehicle.setModel(vehicleRequest.getModel());
            if (vehicleRequest.getRegNumber() != null) vehicle.setRegNumber(vehicleRequest.getRegNumber());
            return vehicleRepository.save(vehicle);
        }
        else return null;
    }

    @DeleteMapping("/vehicles/{id}")
    public boolean deleteVehicle(@PathVariable("id") Long id){
        if(vehicleRepository.existsById(id)){
            vehicleRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    @PostMapping("/vehicles/{vehicleId}/owners/{ownerId}")
    public Vehicle addVehicleToOwner(@PathVariable Long vehicleId,@PathVariable Long ownerId) {
        if(vehicleRepository.existsById(vehicleId) && ownerRepository.existsById(ownerId)){
            Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
            vehicle.setOwner(ownerRepository.findById(ownerId).get());
            return vehicleRepository.save(vehicle);
        }
        else return null;
    }

    @GetMapping("/vehicles/exist/{id}")
    public boolean vehicleExists(@PathVariable("id") Long id){
        return vehicleRepository.existsById(id);
    }

}
