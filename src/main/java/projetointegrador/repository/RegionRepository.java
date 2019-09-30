package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
