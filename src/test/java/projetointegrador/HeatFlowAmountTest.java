package projetointegrador;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import projetointegrador.model.Component;
import projetointegrador.model.ComponentMaterial;
import projetointegrador.model.Face;
import projetointegrador.model.Material;
import projetointegrador.model.enums.Rsi;

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

        component.setTemperatureOutside(new BigDecimal(30));
        component.setTemperatureInside(new BigDecimal(25));

        BigDecimal qfo = component.calculateHeatFlowWinter();

        BigDecimal expectedValue = new BigDecimal(0.3335).setScale(4, RoundingMode.HALF_EVEN);

        Assert.assertEquals(expectedValue, qfo.setScale(4, RoundingMode.HALF_EVEN));
    }

    @Test
    @After
    public void mustBeCalculateHeatFlowSummer () {
        Component component = new Component();
        component.setTransmittance(new BigDecimal(1.92));
        component.setAlpha(new BigDecimal(0.2));
        component.setIndexRadiation(new BigDecimal(458));
        component.setM2(new BigDecimal(4.3));


        component.setTemperatureOutside(new BigDecimal(30));
        component.setTemperatureInside(new BigDecimal(24));

        BigDecimal qfo = component.calculateHeatFlowSummer();

        BigDecimal QFO = component.calculateQFO(qfo);

        BigDecimal expectedValue = new BigDecimal(18.5549).setScale(4, RoundingMode.HALF_EVEN);
        Assert.assertEquals(expectedValue, qfo.setScale(4, RoundingMode.UP));

        BigDecimal expectedValue2 = new BigDecimal(79.7861).setScale(4, RoundingMode.HALF_EVEN);
        Assert.assertEquals(expectedValue2, QFO.setScale(4, RoundingMode.UP));


    }
}

