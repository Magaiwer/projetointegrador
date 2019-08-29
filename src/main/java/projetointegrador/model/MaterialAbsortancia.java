package projetointegrador.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.math.BigDecimal;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "material_absortancia")
@DynamicUpdate
@Data
public class MaterialAbsortancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String superficie;

    @Column
    private BigDecimal alfa;

    @Column
    private BigDecimal beta;
}
