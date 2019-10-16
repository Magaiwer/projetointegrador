package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Audit;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

    @Query("from Audit A left join fetch A.user")
    List<Audit> findAllWithUsers();

}
