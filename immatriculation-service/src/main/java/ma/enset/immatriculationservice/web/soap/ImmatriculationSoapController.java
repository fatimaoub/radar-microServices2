package ma.enset.immatriculationservice.web.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import ma.enset.immatriculationservice.entities.Owner;
import ma.enset.immatriculationservice.entities.Vehicle;
import ma.enset.immatriculationservice.repositories.OwnerRepository;
import ma.enset.immatriculationservice.repositories.VehicleRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@WebService(serviceName = "ImmatriculationWS")
public class ImmatriculationSoapController {

    private  VehicleRepository vehicleRepository;
    private  OwnerRepository ownerRepository;

    //owner
    @WebMethod(operationName = "getOwners")
    public List<Owner> getOwners(){
        return ownerRepository.findAll();
    }


    @WebMethod(operationName = "getOwnerById")
    public Owner getOwnerById(@WebParam(name="id") Long id){
        return ownerRepository.findById(id).get();
    }

    @WebMethod(operationName = "saveOwner")
    public Owner saveOwner(@WebParam(name="ownerRequest") Owner ownerRequest){
        Owner owner = Owner.builder()
                .id(null)
                .name(ownerRequest.getName())
                .email(ownerRequest.getEmail())
                .birthDate(ownerRequest.getBirthDate())
                .build();

        return ownerRepository.save(owner);
    }

    @WebMethod(operationName = "updateOwner")
    public Owner updateOwner(@WebParam(name="id") Long id,@WebParam(name="ownerRequest") Owner ownerRequest){
        if(ownerRepository.existsById(id)){
            Owner owner = ownerRepository.findById(id).get();
            if(ownerRequest.getName() != null) owner.setName(ownerRequest.getName());
            if(ownerRequest.getEmail() != null) owner.setEmail(ownerRequest.getEmail());
            if(ownerRequest.getBirthDate() != null) owner.setBirthDate(ownerRequest.getBirthDate());

            return ownerRepository.save(owner);
        }
        else return null;
    }

    @WebMethod(operationName = "deleteOwner")
    public boolean deleteOwner(@WebParam(name="id") Long id){
        if(ownerRepository.existsById(id)){
            ownerRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    @WebMethod(operationName = "ownerExists")
    public boolean ownerExists(@WebParam(name="id") Long id){
        return ownerRepository.existsById(id);
    }

    //vehicule

    @WebMethod(operationName = "")
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    @WebMethod(operationName = "getVehicleById")
    public Vehicle getVehicleById(@WebParam(name="id") Long id){
        if(vehicleRepository.existsById(id)){
            return vehicleRepository.findById(id).get();
        }
        else return null;
    }

    @WebMethod(operationName = "saveVehicle")
    public Vehicle saveVehicle(@WebParam(name="vehicleRequest")Vehicle vehicleRequest){
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

    @WebMethod(operationName = "updateVehicle")
    public Vehicle updateVehicle(@WebParam(name="id") Long id,@WebParam(name="vehicleRequest") Vehicle vehicleRequest){
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

    @WebMethod(operationName = "deleteVehicle")
    public boolean deleteVehicle(@WebParam(name="id") Long id){
        if(vehicleRepository.existsById(id)){
            vehicleRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    @WebMethod(operationName = "addVehicleToOwner")
    public Vehicle addVehicleToOwner(@WebParam(name="vehicleId") Long vehicleId,@WebParam(name="ownerId") Long ownerId) {
        if(vehicleRepository.existsById(vehicleId) && ownerRepository.existsById(ownerId)){
            Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
            vehicle.setOwner(ownerRepository.findById(ownerId).get());
            return vehicleRepository.save(vehicle);
        }
        else return null;
    }

    @WebMethod(operationName = "vehicleExists")
    public boolean vehicleExists(@WebParam(name="id") Long id){
        return vehicleRepository.existsById(id);
    }

}
