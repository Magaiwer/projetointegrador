package projetointegrador.model.enums;

import java.math.BigDecimal;

public enum Rsi {

    UP(new BigDecimal(0.10)),
    BOTTOM(new BigDecimal(0.17)),
    LEFT_RIGHT(new BigDecimal(0.13));

    private BigDecimal value;

     Rsi(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
