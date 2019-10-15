package projetointegrador.repository;

import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetointegrador.model.Component;
import projetointegrador.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>
{
    ObservableList<Room> findByProject(Long id);
}
