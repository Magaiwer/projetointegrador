package projetointegrador.model.enums;

import projetointegrador.model.Component;
import projetointegrador.model.Face;

import java.math.BigDecimal;

public enum FlowType {

    SUMMER("Verão") {
        @Override
        public BigDecimal calculateHeatFlow(Component component) {
           return component.calculateHeatFlowSummer();
        }
    },
    WINTER("Inverno") {
        @Override
        public BigDecimal calculateHeatFlow(Component component) {
           return component.calculateHeatFlowWinter();
        }
    };

    private String name;

    FlowType(String name) {
        this.name = name;
    }

    public abstract BigDecimal calculateHeatFlow(Component component);

}
