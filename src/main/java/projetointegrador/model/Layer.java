package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "layer")
@Data
@ToString(exclude = "face")
@EqualsAndHashCode(exclude = "face")
public class Layer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal thickness;

    @Column
    private BigDecimal resistenceTotal;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;

    @ManyToMany()
    @JoinTable(name = "layer_material",
            joinColumns = @JoinColumn(name = "layer_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;

    @OneToMany(mappedBy = "layer")
    List<LayerMaterial> layerMaterials;


    public BigDecimal getResistenceTotal() {
        return this.layerMaterials
                .stream()
                .map(LayerMaterial::getResistence)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
