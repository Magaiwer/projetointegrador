package projetointegrador.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "component_material")
@Data
public class ComponentMaterial {

    @EmbeddedId
    private ComponentMaterialId id;

    @ManyToOne
    @JoinColumn(name = "component_id", insertable = false, updatable = false)
    private Component component;

    @ManyToOne
    @JoinColumn(name = "material_id", insertable = false, updatable = false)
    private Material material;

    private BigDecimal thickness;


    private BigDecimal resistance;

    public BigDecimal calculateResistance(BigDecimal  thickness) {
        return calculateResistance(thickness, this.material.getCondutividadeTermica());
    }

    private BigDecimal calculateResistance(BigDecimal thickness, BigDecimal thermalCondutivity) {
        return this.resistance = thickness.divide(thermalCondutivity, RoundingMode.CEILING);
    }

}
