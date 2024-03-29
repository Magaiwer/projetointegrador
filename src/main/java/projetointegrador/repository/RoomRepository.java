package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Nullable
    List<Room> findByProjectId(Long id);

    @Nullable
    @Query("select distinct r from Room r left outer join  fetch  r.faces where r.project.id = :id")
    List<Room> findByProjectWithFaces(@Param("id") Long id);

/*    @Query(" select avg(tpm.totalroom) from (count (r.id) as totalroom from project p inner join room r on p.id = r.project_id group by p.id) as tpm  ")
    double averageRoomByProject();*/



}
