package projetointegrador.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Entities;

import java.util.Optional;

@Repository
public interface EntitiesRepository  extends JpaRepository<Entities, Long> {

    Optional<Entities> findByNameIgnoreCase(String name);
}
