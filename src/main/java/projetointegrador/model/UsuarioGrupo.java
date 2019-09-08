package projetointegrador.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario_grupo")
@Getter @Setter @EqualsAndHashCode
public class UsuarioGrupo implements Serializable {

    @EmbeddedId
    private UsuarioGrupoId id;
}
