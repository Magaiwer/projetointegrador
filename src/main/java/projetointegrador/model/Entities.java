package projetointegrador.model;

import lombok.Data;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@EntityListeners(AuditListeners.class)
@Table(name = "entity")
@Data
public class Entities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "target")
    private String target;

    @OneToMany(mappedBy = "entity")
    private List<Form> forms;
}
