package ma.enset.billingservice.entities;

import lombok.Data;

@Data
public class DepassementVitesseRequest {

        private Long radarId;
        private Long vehiculeID;
        private int vitesse;
        private int vitesseMaximale;

}
