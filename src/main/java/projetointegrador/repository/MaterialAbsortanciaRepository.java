package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.MaterialAbsortancia;

@Repository
public interface MaterialAbsortanciaRepository extends JpaRepository<MaterialAbsortancia, Long> {
}
