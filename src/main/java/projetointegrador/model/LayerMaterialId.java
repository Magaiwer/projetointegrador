package projetointegrador.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LayerMaterialId implements Serializable {

    @Column(name = "layer_id")
    private Layer layerId;

    @Column(name = "material_id")
    private Long materialId;


}
