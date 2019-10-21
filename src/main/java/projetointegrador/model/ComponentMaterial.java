package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "component_material")
@Data @EqualsAndHashCode
public class ComponentMaterial implements Serializable {

    @EmbeddedId
    private ComponentMaterialId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("componentId")
    private Component component;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("materialId")
    private Material material;

    @Column
    private BigDecimal thickness;

    @Column
    private BigDecimal resistance;


    public BigDecimal calculateResistance() {
        return this.resistance = this.thickness.divide(material.getCondutividadeTermica(), RoundingMode.HALF_EVEN);
    }

}
