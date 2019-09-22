package projetointegrador.model;

import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String fone;

    @Column
    private Date birthDate;

}
