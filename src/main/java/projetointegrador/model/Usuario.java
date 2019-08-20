package projetointegrador.model;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "usuario")
@DynamicUpdate
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "ativo")
    private boolean ativo;

    @Transient
    private String confirmacaoSenha;

    public boolean isNew() {
        return this.id == null;
    }



}
