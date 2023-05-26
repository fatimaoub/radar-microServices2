package ma.enset.billingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Proprietaire {
    private Long id;
    private String nom;
    private Date dateNaissance;
    private String email;
    private String telephone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Vehicule> vehicules;
}
