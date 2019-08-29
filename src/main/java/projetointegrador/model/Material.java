package projetointegrador.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.event.EventListener;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "material")
@DynamicUpdate
@Data
public class Material
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private BigDecimal condutividadeTermica;
}
