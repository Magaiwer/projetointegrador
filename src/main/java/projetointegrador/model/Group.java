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
@Table(name = "group", schema = "public")
@DynamicUpdate
@Data @ToString(exclude={"permissions"})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_permission",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;
}
