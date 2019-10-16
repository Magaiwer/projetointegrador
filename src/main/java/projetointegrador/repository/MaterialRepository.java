package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
