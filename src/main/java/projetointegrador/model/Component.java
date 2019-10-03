package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "component")
@Data
@ToString(exclude = "face")
@EqualsAndHashCode(exclude = "face")
public class Component implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal thickness;

    @Column
    private BigDecimal resistanceTotal;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;

    @ManyToMany()
    @JoinTable(name = "component_material",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;

    @OneToMany(mappedBy = "component")
    List<ComponentMaterial> componentMaterials;


    public BigDecimal getResistenceTotal() {
        return this.componentMaterials
                .stream()
                .map(ComponentMaterial::getResistance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
