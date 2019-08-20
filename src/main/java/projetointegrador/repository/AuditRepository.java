package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Audit;

@Repository
@Transactional
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
