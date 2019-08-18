package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
