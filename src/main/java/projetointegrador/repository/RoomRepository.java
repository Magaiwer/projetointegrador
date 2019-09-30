package projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
