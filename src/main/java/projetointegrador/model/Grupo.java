package projetointegrador.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.event.EventListener;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "grupo")
@DynamicUpdate
@Data @ToString(exclude={"permissions"})
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "grupo_permission",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;
}
