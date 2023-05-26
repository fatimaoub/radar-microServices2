package ma.enset.infractionservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity@Data
@Builder@AllArgsConstructor@NoArgsConstructor@ToString
public class Infraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private Long radarId;
    private Long vehicleID;
    private int vehicleSpeed;
    private int radarMaxSpeed;
    private double infractionAmount;

    public Infraction(String date, Long radarId, String vehicleMatricule, int vehicleSpeed, int radarMaxSpeed, double infractionAmount) {
    }
}