package projetointegrador.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Entities;

import java.util.Optional;

@Repository
public interface EntityRepository  extends JpaRepository<Entities, Long> {

    Optional<Entities> findByName(String name);
}
