package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Person;
import projetointegrador.model.Region;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("from Project p left join fetch p.region r where r.id = :id")
    List<Person> findProjectsByRegion(@Param("id") Long id);
}
