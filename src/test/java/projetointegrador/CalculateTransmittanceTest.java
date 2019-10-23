package projetointegrador;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import projetointegrador.model.Component;
import projetointegrador.model.ComponentMaterial;
import projetointegrador.model.Face;
import projetointegrador.model.Material;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class CalculateTransmittanceTest {

    @Test
    @After
    public void mustBeCalculateTransmittance() {

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

        component1.calculateResistanceTotal();
        component1.calculateTransmittance();

        BigDecimal expectedValue = new BigDecimal(9.1700).setScale(4, RoundingMode.HALF_EVEN);

        Assert.assertEquals(expectedValue, component1.getResistanceTotal());
        Assert.assertEquals(new BigDecimal(0.1091).setScale(4, RoundingMode.HALF_EVEN), component1.getTransmittance());

    }

    @Test
    @After
    public void mustBeCalculateTransmittanceAverage() {

        // COMPONENTE 1
        Component component1 = new Component();
        component1.setId(1L);
        component1.setRsi(new BigDecimal(0.13));

        ComponentMaterial componentMaterial1 = builderComponent(new BigDecimal(2), new BigDecimal(10), component1);

        ComponentMaterial componentMaterial2 = builderComponent(new BigDecimal(2), new BigDecimal(20), component1);

        component1.setComponentMaterials(new HashSet<>(Arrays.asList(componentMaterial1, componentMaterial2)));

        component1.getComponentMaterials()
                .forEach(ComponentMaterial::calculateResistance);

        component1.calculateResistanceTotal();
        component1.calculateTransmittance();

        // COMPONENTE 2
        Component component2 = new Component();
        component2.setId(2L);
        component2.setRsi(new BigDecimal(0.13));

        ComponentMaterial componentMaterial3 = builderComponent(new BigDecimal(2), new BigDecimal(10), component2);
        ComponentMaterial componentMaterial4 = builderComponent(new BigDecimal(2), new BigDecimal(20), component2);

        component2.setComponentMaterials(new HashSet<>(Arrays.asList(componentMaterial3, componentMaterial4)));

        component2.getComponentMaterials()
                .forEach(ComponentMaterial::calculateResistance);

        component2.calculateResistanceTotal();
        component2.calculateTransmittance();

        Face face1 = new Face();
        face1.setComponents(new HashSet<>(Arrays.asList(component1, component2)));
        face1.calculateTransmittanceAverage();

        BigDecimal expectedValue = new BigDecimal(15.1700).setScale(4, RoundingMode.HALF_EVEN);

        // COMPONENTE 1
        Assert.assertEquals(expectedValue, component1.getResistanceTotal());
        Assert.assertEquals(new BigDecimal(0.0659).setScale(4, RoundingMode.HALF_EVEN), component1.getTransmittance());

        // COMPONENTE 2
        Assert.assertEquals(expectedValue, component1.getResistanceTotal());
        Assert.assertEquals(new BigDecimal(0.0659).setScale(4, RoundingMode.HALF_EVEN), component1.getTransmittance());

        Assert.assertEquals(new BigDecimal(0.0659).setScale(4, RoundingMode.HALF_EVEN), face1.getTransmittanceAverage());

    }

    private ComponentMaterial builderComponent(BigDecimal thermalConductivity, BigDecimal thickness, Component component) {

        Material material1 = new Material();
        material1.setCondutividadeTermica(thermalConductivity);
        material1.setId(new Random().nextLong());

        ComponentMaterial componentMaterial1 = new ComponentMaterial();
        componentMaterial1.setThickness(thickness);
        componentMaterial1.setMaterial(material1);
        componentMaterial1.setComponent(component);

        return componentMaterial1;
    }


}

