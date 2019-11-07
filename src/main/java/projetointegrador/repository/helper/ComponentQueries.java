package projetointegrador.repository.helper;

import org.springframework.lang.Nullable;
import projetointegrador.model.Component;
import projetointegrador.repository.filter.ProjectFilter;

import java.util.List;

public interface ComponentQueries {

    @Nullable
    List<Component> findByProject(ProjectFilter filter);
}
