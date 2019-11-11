package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Component;
import projetointegrador.model.ComponentMaterial;
import projetointegrador.repository.ComponentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ComponentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentService.class);

    @Autowired
    private ComponentRepository componentRepository;

    @Transactional
    public List<Component> saveAll(List<Component> components) {
        return componentRepository.saveAll(components);
    }

    @Transactional
    public void save(Component component) {

        try {
            calculateResistance(component);
            calculateResistanceTotal(component);
            calculateTransmittance(component);
            componentRepository.saveAndFlush(component);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), component);
        }

    }

    private void calculateResistance(Component component) {
        component.getComponentMaterials().forEach(ComponentMaterial::calculateResistance);
    }

    private void calculateResistanceTotal(Component component) {
        component.calculateResistanceTotal();
    }

    public void calculateTransmittance(Component component) {
        calculateResistance(component);
        calculateResistanceTotal(component);
        component.calculateTransmittance();
    }


    public void calculateQFO(Component component) {
        BigDecimal qfo = component.getFlowType().calculateHeatFlow(component);
        component.calculateQFO(qfo);
    }

    public void calculateQFT(Component component) {
        if (component.getTransmittanceGlass() != null && component.getSolarFactor() != null) {
            component.calculateQFT();
        }
    }

    private void calculateThermalLoadFace(Component component) {
        component.getFace().addComponent(component);
        component.getFace().calculateThermalLoad();
    }

    public void calculateAllIndexComponent(Component component) {
        calculateTransmittance(component);

        if (component.getTransmittanceGlass() != null) {
            calculateQFT(component);
        } else {
            calculateQFO(component);
        }
        calculateThermalLoadFace(component);
    }
}
