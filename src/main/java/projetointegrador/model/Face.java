package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "face")
@Data
@ToString(exclude = {"room", "components"})
@EqualsAndHashCode(exclude = {"room", "components"})
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
    private List<Component> components;

/*    private BigDecimal calculateTransmittance() {
        /return this.transmittance = new BigDecimal(1).divide(getTotalResistence(), RoundingMode.CEILING);
    }*/



}
