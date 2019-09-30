package projetointegrador.model;

import lombok.Data;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "person")
@Data
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
    private LocalDate birthDate;


}
