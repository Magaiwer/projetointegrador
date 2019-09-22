package projetointegrador.model;


import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "user",schema = "public")
@DynamicUpdate
@Data @ToString(exclude={"groups"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;

    @Transient
    private String confimationPassword;

    @Transient
    private String labelActive;

    public boolean isNew() {
        return this.id == null;
    }


    public String getLabelActive() {
        return this.active ? "Sim" : "Não";
    }



}
