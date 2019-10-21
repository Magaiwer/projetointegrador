package projetointegrador.model.enums;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public enum Rsi {

    UP(new BigDecimal(0.10), "Superior"),
    BOTTOM(new BigDecimal(0.17), "Lateral"),
    LEFT_RIGHT(new BigDecimal(0.13), "Inferior");

    private BigDecimal value;

    private String description;

     Rsi(BigDecimal value, String description) {
         this.value = value;
         this.description = description;
    }

}
