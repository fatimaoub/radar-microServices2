package ma.enset.billingservice.repository;

import ma.enset.billingservice.entities.Radar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarRespository extends JpaRepository<Radar, Long> {
}
