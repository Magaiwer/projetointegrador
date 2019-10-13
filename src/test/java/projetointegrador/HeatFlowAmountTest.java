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
import java.util.Random;

public class HeatFlowAmountTest {

    @Test
    @After
    public void mustBeCalculateWinterHeatFlow () {
        Component component = new Component();
        component.setTransmittance(new BigDecimal(0.0667));

        BigDecimal qfo = component.calculateHeatFlowWinter(new BigDecimal(30), new BigDecimal(25));

        BigDecimal expectedValue = new BigDecimal(0.3335).setScale(4, RoundingMode.HALF_EVEN);

        Assert.assertEquals(expectedValue, qfo.setScale(4, RoundingMode.HALF_EVEN));
    }
}

