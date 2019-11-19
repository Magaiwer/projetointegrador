package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Component;
import projetointegrador.repository.helper.ComponentQueries;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long>, ComponentQueries {

    @Nullable
    @Query("select distinct  c from Component c left join fetch c.componentMaterials m where c.face.id = :id")
    List<Component> findByFace(@Param("id") Long id);
}
