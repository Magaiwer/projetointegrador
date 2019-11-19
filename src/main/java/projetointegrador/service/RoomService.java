package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Project;
import projetointegrador.model.Room;
import projetointegrador.repository.RoomRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public List<Room> saveAll(List<Room> roomList)
    {
       return roomRepository.saveAll(roomList);
    }

    @Transactional
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public void delete(Room room) {

        if(!room.isNew()) {
            roomRepository.delete(room);
        }
    }
}
