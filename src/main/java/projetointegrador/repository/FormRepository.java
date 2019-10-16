package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Form;
import projetointegrador.repository.helper.FormQueries;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> , FormQueries {

    @Query("from Form f left join fetch  f.permissions ")
    List<Form> findFormWithPermissions();
}
