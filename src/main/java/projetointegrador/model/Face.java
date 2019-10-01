package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "face")
@Data
@ToString(exclude = {"room" , "layers"}) @EqualsAndHashCode(exclude = {"room","layers"})
public class Face implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany
    private List<Layer> layers;
}
