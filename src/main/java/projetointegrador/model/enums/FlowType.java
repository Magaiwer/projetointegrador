package projetointegrador.model.enums;

import projetointegrador.model.Component;
import projetointegrador.model.Face;

import java.math.BigDecimal;

public enum FlowType {

    SUMMER("Verão") {
        @Override
        public void calculateHeatFlow(Component component, BigDecimal outsideTemperature, BigDecimal insideTemperature) {
            component.calculateHeatFlowSummer(outsideTemperature, insideTemperature);
        }
    },
    WINTER("Inverno") {
        @Override
        public void calculateHeatFlow(Component component, BigDecimal outsideTemperature, BigDecimal insideTemperature) {
            component.calculateHeatFlowWinter(outsideTemperature, insideTemperature);
        }
    };

    private String name;

    FlowType(String name) {
        this.name = name;
    }

    public abstract void calculateHeatFlow(Component component, BigDecimal outsideTemperature, BigDecimal insideTemperature);

}
