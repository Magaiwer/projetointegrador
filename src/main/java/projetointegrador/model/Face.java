package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Entity
@Table(name = "face")
@Data
@ToString(exclude = {"room", "layers"})
@EqualsAndHashCode(exclude = {"room", "layers"})
public class Face implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal transmittance;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "face", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Layer> layers;

/*    private BigDecimal calculateTransmittance() {
        /return this.transmittance = new BigDecimal(1).divide(getTotalResistence(), RoundingMode.CEILING);
    }*/



}
