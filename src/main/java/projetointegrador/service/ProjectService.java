package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import projetointegrador.model.Project;
import projetointegrador.repository.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public void save(Project project) {
        try {

            if (project.isNew()) {
                project.setDate(LocalDateTime.now());
            }
            projectRepository.saveAndFlush(project);

        } catch (RuntimeException ex) {
            LOGGER.error("Erro ao salvar projeto", ex);
        }
    }

    public void delete(Project project) {
        try {
            projectRepository.delete(project);
        } catch (Exception ex) {
            LOGGER.error("Problema ao deletar projeto", ex);
        }
    }
}
