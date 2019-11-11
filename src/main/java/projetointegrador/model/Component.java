package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import projetointegrador.listeners.AuditListeners;
import projetointegrador.model.enums.FlowType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "component")
@Data
@ToString(exclude = {"face", "componentMaterials"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class Component implements Serializable {

    @Transient
    private final BigDecimal RSE = new BigDecimal(0.04);

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal resistanceTotal = BigDecimal.ZERO;


    @Column
    private BigDecimal transmittance = BigDecimal.ZERO;

    @Column
    private BigDecimal alpha = BigDecimal.ZERO;

    @Column
    private BigDecimal indexRadiation = BigDecimal.ZERO;

    @Column
    private BigDecimal qfo = BigDecimal.ZERO;

    @Column
    private BigDecimal qft = BigDecimal.ZERO;

    @Column
    private BigDecimal m2 = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComponentMaterial> componentMaterials;

    @Transient
    private BigDecimal rsi;

    @Transient
    private FlowType flowType;

    @Transient
    private BigDecimal transmittanceGlass = BigDecimal.ZERO;

    @Transient
    private BigDecimal solarFactor = BigDecimal.ZERO;

    @Transient
    private BigDecimal temperatureOutside = BigDecimal.ZERO;

    @Transient
    private BigDecimal temperatureInside = BigDecimal.ZERO;

    public BigDecimal calculateResistanceTotal() {
        BigDecimal resistanceSum = this.componentMaterials
                .stream()
                .map(ComponentMaterial::getResistance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return this.resistanceTotal =
                RSE.add(resistanceSum).add(this.rsi).setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateTransmittance() {
        return this.transmittance = new BigDecimal(1).divide(this.resistanceTotal, 4, RoundingMode.HALF_EVEN);
    }

    public void addMaterial(Material material, BigDecimal thickness) {
        ComponentMaterial componentMaterial = new ComponentMaterial();
        componentMaterial.setThickness(thickness);
        componentMaterial.setMaterial(material);
        componentMaterial.setComponent(this);

        componentMaterial.setId(new ComponentMaterialId(this.getId(), material.getId()));

        if (this.componentMaterials == null) {
            this.componentMaterials = new HashSet<>();
        }

        this.componentMaterials.add(componentMaterial);
    }

    public BigDecimal calculateHeatFlowWinter() {
        return this.transmittance
                .multiply(this.temperatureOutside.subtract(this.temperatureInside).abs())
                .setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateHeatFlowSummer() {
        BigDecimal deltaTemperature = this.temperatureOutside.subtract(this.temperatureInside).abs();

        return this.transmittance.multiply(this.alpha.multiply(this.indexRadiation).multiply(RSE).add(deltaTemperature))
                .setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateQFO(BigDecimal qfo) {
        return this.qfo = qfo.multiply(this.m2);
    }


    public BigDecimal calculateQFT() {
        this.qft = this.transmittanceGlass.multiply(this.temperatureOutside
                .subtract(this.temperatureInside).abs()).add(this.solarFactor.multiply(this.indexRadiation));
        return this.qft.multiply(m2);
    }

    public boolean isNew() {
        return this.id == null;
    }


}
