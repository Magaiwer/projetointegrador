package projetointegrador.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ComponentMaterialId implements Serializable {

    @Column(name = "component_id")
    private Component componentId;

    @Column(name = "material_id")
    private Long materialId;


}
