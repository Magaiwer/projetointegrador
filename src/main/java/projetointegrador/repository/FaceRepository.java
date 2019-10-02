package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Face;

import java.util.List;

@Repository
public interface FaceRepository extends JpaRepository<Face, Long> {

    @Query("from Face f inner join fetch f.layers l inner join l.materials where f.id =:id  ")
    List<Face> findByIdWithLaywers(@Param("id") Long id);
}
