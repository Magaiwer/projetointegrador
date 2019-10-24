package projetointegrador.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class ComponentMaterialId implements Serializable {

    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "material_id")
    private Long materialId;


}
