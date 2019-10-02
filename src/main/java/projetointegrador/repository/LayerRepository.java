package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Layer;

import java.util.List;

@Repository
public interface LayerRepository extends JpaRepository<Layer, Long> {

    @Query("from Layer l inner join fetch l.materials m where l.face.id = :id")
    List<Layer> findByFace(@Param("id") Long id);
}
