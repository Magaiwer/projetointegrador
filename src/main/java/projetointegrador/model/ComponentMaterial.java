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

    @Column
    private BigDecimal thickness;

    @Column
    private BigDecimal resistance;


    public BigDecimal calculateResistance() {
        return this.resistance = this.thickness.divide(material.getCondutividadeTermica(), RoundingMode.CEILING);
    }

}
