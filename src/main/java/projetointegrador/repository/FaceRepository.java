package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Face;

import java.util.List;

@Repository
public interface FaceRepository extends JpaRepository<Face, Long> {

    @Query("select distinct f from Face f inner join fetch f.components c where f.id =:id ")
    List<Face> findByIdWithComponents(@Param("id") Long id);
}
