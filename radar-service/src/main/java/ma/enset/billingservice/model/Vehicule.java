package ma.enset.billingservice.model;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Vehicule {
    private Long id;
    private int numeroMatricule;
    private String marque;
    private int puissanceFiscale;
    private String modele;
    private Proprietaire proprietaire;

}
