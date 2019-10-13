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

    public BigDecimal calculateHeatFlowWinter(BigDecimal outsideTemperature, BigDecimal insideTemperature ) {
        return this.transmittanceAverage
                .multiply(outsideTemperature.subtract(insideTemperature)).setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateHeatFlowSummer(BigDecimal outsideTemperature, BigDecimal insideTemperature ) {
        return this.transmittanceAverage
                .multiply(outsideTemperature.subtract(insideTemperature)).setScale(4, RoundingMode.HALF_EVEN);
    }


}
