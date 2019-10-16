package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
