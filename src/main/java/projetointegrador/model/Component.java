package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Entity
@Table(name = "component")
@Data
@ToString(exclude = {"face", "componentMaterials"})
@EqualsAndHashCode(exclude = {"face", "componentMaterials"})
@DynamicUpdate
public class Component implements Serializable {

    @Transient
    private final BigDecimal RSE = new BigDecimal(0.04);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal resistanceTotal;


    @Column
    private BigDecimal transmittance;

    @Column
    private BigDecimal alpha;

    @Column
    private BigDecimal indexRadiation;

    @Column
    private BigDecimal qfo;

    @Column
    private BigDecimal qft;

    @Column
    private BigDecimal m2;

    @ManyToOne
    @JoinColumn(name = "face_id")
    private Face face;

    @ManyToMany()
    @JoinTable(name = "component_material",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;

    @OneToMany(mappedBy = "component")
    private List<ComponentMaterial> componentMaterials;

    @Transient
    private BigDecimal rsi;

    @Transient
    private BigDecimal transmittanceGlass;

    @Transient
    private BigDecimal solarFactor;

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

    public void addMaterial(Material material) {
        this.getComponentMaterials().forEach(componentMaterial -> componentMaterial.setMaterial(material));
    }

    public BigDecimal calculateHeatFlowWinter(BigDecimal outsideTemperature, BigDecimal insideTemperature) {
        return this.transmittance
                .multiply(outsideTemperature.subtract(insideTemperature).abs()).setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateHeatFlowSummer(BigDecimal outsideTemperature, BigDecimal insideTemperature) {
        BigDecimal deltaTemperature = outsideTemperature.subtract(insideTemperature).abs();

        return this.transmittance.multiply(this.alpha.multiply(this.indexRadiation).multiply(RSE).add(deltaTemperature))
                .setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateQFO(BigDecimal qfo) {
        return this.qfo = qfo.multiply(this.m2);
    }


    public BigDecimal calculateQFT(BigDecimal outsideTemperature, BigDecimal insideTemperature) {
        this.qft = this.transmittanceGlass.multiply(outsideTemperature.subtract(insideTemperature).abs()).add(this.solarFactor.multiply(this.indexRadiation));
        return this.qft.multiply(m2);
    }


}
