package projetointegrador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@EntityListeners(AuditListeners.class)
@Table(name = "form")
@DynamicUpdate
@Data @ToString(exclude="entity") @EqualsAndHashCode(of="entity")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean audit;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    private Entities entity;
}
