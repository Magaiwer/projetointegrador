package projetointegrador;

import org.assertj.core.util.Sets;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import projetointegrador.model.Component;
import projetointegrador.model.ComponentMaterial;
import projetointegrador.model.Material;
import projetointegrador.repository.FaceRepository;
import projetointegrador.repository.ComponentRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class CalculateResistanceTest {

    @Autowired(required = true)
    private FaceRepository faceRepository;

    @Autowired(required = true)
    private ComponentRepository componentRepository;


    @Test
    @After
    public void mustBeCalculateResistanceOneComponentMaterial() {

        Component component1 = new Component();
        component1.setId(1L);
        component1.setRsi(new BigDecimal(0.13));

        Material material1 = new Material();
        material1.setCondutividadeTermica(new BigDecimal(2));
        material1.setId(1L);

        ComponentMaterial componentMaterial1 = new ComponentMaterial();
        componentMaterial1.setThickness(new BigDecimal(10));
        componentMaterial1.setMaterial(material1);
        componentMaterial1.setComponent(component1);

        Assert.assertEquals(new BigDecimal(5), componentMaterial1.calculateResistance());


    }

    @Test
    @After
    public void mustBeCalculateResistanceTotal() {

        Component component1 = new Component();
        component1.setId(1L);
        component1.setRsi(new BigDecimal(0.13));

        Material material1 = new Material();
        material1.setCondutividadeTermica(new BigDecimal(2));
        material1.setId(1L);

        Material material2 = new Material();
        material2.setCondutividadeTermica(new BigDecimal(2));
        material2.setId(2L);

        ComponentMaterial componentMaterial1 = new ComponentMaterial();
        componentMaterial1.setThickness(new BigDecimal(10));
        componentMaterial1.setMaterial(material1);
        componentMaterial1.setComponent(component1);

        ComponentMaterial componentMaterial2 = new ComponentMaterial();
        componentMaterial2.setThickness(new BigDecimal(8));
        componentMaterial2.setMaterial(material2);
        componentMaterial2.setComponent(component1);

        component1.setComponentMaterials(new HashSet<>(Arrays.asList(componentMaterial1, componentMaterial2)));

        component1.getComponentMaterials()
                .forEach(ComponentMaterial::calculateResistance);

        BigDecimal expectedValue = new BigDecimal(9.1700).setScale(4, RoundingMode.HALF_EVEN);
        Assert.assertEquals(expectedValue, component1.calculateResistanceTotal());

    }


}

