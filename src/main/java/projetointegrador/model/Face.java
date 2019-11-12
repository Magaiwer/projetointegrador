package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "face")
@Data
@ToString(exclude = {"room", "components"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class Face implements Serializable {

    @Transient
    private final BigDecimal BTUS = new BigDecimal(3412).setScale(1, RoundingMode.HALF_EVEN);

    @EqualsAndHashCode.Include
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
    private Set<Component> components = new HashSet<>();

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
            this.components = new HashSet<>();
        }

        this.components.add(component);
    }

    public boolean isNew() {
        return this.id == null;
    }


}
