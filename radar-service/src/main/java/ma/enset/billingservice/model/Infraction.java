package ma.enset.billingservice.model;

import lombok.Data;

@Data
public class Infraction {
    private Long id;
    private String date;
    private Long radarId;
    private Long vehicleID;
    private int vehicleSpeed;
    private int radarMaxSpeed;
    private double infractionAmount;
}
