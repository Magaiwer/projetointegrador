package projetointegrador.model;

import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private Date dataNascimento;

}
