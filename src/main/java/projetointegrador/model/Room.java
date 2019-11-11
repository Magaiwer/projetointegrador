package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "room")
@Data
@ToString(exclude = {"project", "faces"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room implements Serializable {

    @Transient
    private final BigDecimal BTUS = new BigDecimal(3412).setScale(1, RoundingMode.HALF_EVEN);

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Face> faces;

    public BigDecimal calculateBtus() {
        BigDecimal thermalLoadSum = this.faces
                .stream()
                .map(Face::getThermalLoad)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return thermalLoadSum.multiply(BTUS)
                .divide(new BigDecimal(1000))
                .setScale(2, RoundingMode.HALF_EVEN);

    }

    public boolean isNew() {
        return this.id == null;
    }

}
