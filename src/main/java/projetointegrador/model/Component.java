package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Entity
@Table(name = "component")
@Data
@ToString(exclude = "face")
@EqualsAndHashCode(exclude = "face")
public class Component implements Serializable {

    private final BigDecimal RSE = new BigDecimal(0.04);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal resistanceTotal;

    @Column
    private BigDecimal transmittance;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;

    @ManyToMany()
    @JoinTable(name = "component_material",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;

    @OneToMany(mappedBy = "component")
    private List<ComponentMaterial> componentMaterials;

    @Transient
    private BigDecimal rsi;

    public BigDecimal calculateResistanceTotal() {
        return this.resistanceTotal =
                RSE.add(
                this.componentMaterials
                .stream()
                .map(ComponentMaterial::getResistance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO))
                .add(this.rsi).setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateTransmittance() {
        return this.transmittance = new BigDecimal(1).divide(this.resistanceTotal,4, RoundingMode.HALF_EVEN);
    }

    public void addMaterial(Material material) {
        this.getComponentMaterials().forEach(componentMaterial -> componentMaterial.setMaterial(material));
    }


}
