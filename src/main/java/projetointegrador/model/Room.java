package projetointegrador.model;

import javafx.beans.property.SimpleStringProperty;
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
@Data @ToString(exclude = "project") @EqualsAndHashCode(exclude = "project")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /*
    @OneToMany
    private List<Face> faces;
    */
}
