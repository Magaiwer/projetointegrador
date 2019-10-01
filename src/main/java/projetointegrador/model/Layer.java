package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


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
    private String name;

    @Column
    private BigDecimal thinckness;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;

    @ManyToMany()
    @JoinTable(name = "layer_material",
            joinColumns = @JoinColumn(name = "layer_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;
}
