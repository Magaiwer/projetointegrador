package projetointegrador.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.event.EventListener;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import javax.persistence.Entity;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "grupo")
@DynamicUpdate
@Data
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
}
