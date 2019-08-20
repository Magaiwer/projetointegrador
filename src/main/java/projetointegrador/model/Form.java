package projetointegrador.model;

import lombok.Data;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@EntityListeners(AuditListeners.class)
@Table(name = "form")
@Data
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
