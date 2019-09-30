package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Layer;

@Repository
public interface LayerRepository extends JpaRepository<Layer, Long> {

}
