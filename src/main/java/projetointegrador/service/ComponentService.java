package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Component;
import projetointegrador.model.ComponentMaterial;
import projetointegrador.repository.ComponentRepository;

import java.util.List;

@Service
public class ComponentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentService.class);

    @Autowired
    private ComponentRepository componentRepository;

    @Transactional
    public List<Component> saveAll(List<Component> components)
    {
        return componentRepository.saveAll(components);
    }

    @Transactional
    public void save(Component component)
    {
        component.getComponentMaterials().forEach(ComponentMaterial::calculateResistance);
        System.out.println(component);
        componentRepository.saveAndFlush(component);
    }
}
