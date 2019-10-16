package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Permission;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("from Permission p left join fetch  p.form ")
    List<Permission> findPermissionWithForms();
}
