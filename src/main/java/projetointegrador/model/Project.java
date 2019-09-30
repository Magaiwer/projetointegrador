package projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "project")
@Data @ToString(exclude = "person") @EqualsAndHashCode(exclude = "person")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany
    private List<Room> rooms;




}
