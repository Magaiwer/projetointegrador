package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Face;

@Repository
public interface FaceRepository extends JpaRepository<Face, Long> {

}
