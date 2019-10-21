package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
    private BigDecimal transmittanceAverage;

    @Column
    private BigDecimal thermalLoad;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "face", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Component> components;

    public BigDecimal calculateTransmittanceAverage() {
        this.transmittanceAverage = BigDecimal.valueOf(this.components
                .stream()
                .map(Component::getTransmittance)
                .mapToDouble(BigDecimal::doubleValue)
                .average().orElse(BigDecimal.ZERO.doubleValue()));

        return this.transmittanceAverage;
    }

    public BigDecimal calculateThermalLoad() {
        return this.thermalLoad = this.components.stream()
                .map(component -> component.getQfo().add(component.getQft()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public void addComponent(Component component) {
        if (this.components == null) {
            this.components = new ArrayList<>();
        }

        this.components.add(component);
    }


}
