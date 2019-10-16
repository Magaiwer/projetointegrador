package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@EntityListeners(AuditListeners.class)
@Table(name = "entity")
@Data @ToString(exclude="forms") @EqualsAndHashCode(of="forms")
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
