package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Face;
import projetointegrador.repository.FaceRepository;

@Service
public class FaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaceService.class);

    @Autowired
    private FaceRepository faceRepository;

    @Transactional
    public void save(Face face)
    {
        faceRepository.saveAndFlush(face);
    }
}
