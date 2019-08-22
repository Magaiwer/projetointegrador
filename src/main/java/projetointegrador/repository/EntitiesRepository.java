package projetointegrador.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Entities;

import java.util.List;

@Repository
public interface EntitiesRepository  extends JpaRepository<Entities, Long> {

    @Query("select e from Entities e INNER JOIN e.forms")
    List<Entities> findEntitiesWithForms();
}
