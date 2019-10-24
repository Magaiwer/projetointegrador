package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "room")
@Data @ToString(exclude = {"project", "faces"}) @EqualsAndHashCode(exclude = {"project", "faces"})
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @OneToMany(mappedBy = "room")
    private List<Face> faces;

}
