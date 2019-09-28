package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "layer")
@Data
@ToString(exclude = "face")
@EqualsAndHashCode(exclude = "face")
public class Layer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;
}
