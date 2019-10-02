package projetointegrador.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "layer_material")
@Data
public class LayerMaterial {

    @EmbeddedId
    private LayerMaterialId id;

    @ManyToOne
    @JoinColumn(name = "layer_id", insertable = false, updatable = false)
    private Layer layer;

    @ManyToOne
    @JoinColumn(name = "material_id", insertable = false, updatable = false)
    private Material material;

    private BigDecimal resistence;

    public BigDecimal calculateResistence(BigDecimal  thickness) {
        return calculateResistence(thickness, this.material.getCondutividadeTermica());
    }

    private BigDecimal calculateResistence(BigDecimal thickness, BigDecimal thermalCondutivity) {
        return this.resistence = thickness.divide(thermalCondutivity, RoundingMode.CEILING);
    }

}
