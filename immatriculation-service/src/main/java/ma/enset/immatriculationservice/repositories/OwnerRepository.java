package ma.enset.immatriculationservice.repositories;

import ma.enset.immatriculationservice.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
