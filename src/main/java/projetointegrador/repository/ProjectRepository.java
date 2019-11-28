package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectById(@Param("id") Long id);

    List<Project> findProjectByIdAndRegionId(@Param("id") Long id, @Param("region_id") Long region_id);

    List<Project> findByRegionId(@Param("region_id") Long region_id);


}
