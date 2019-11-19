package projetointegrador.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "region")
@Data
@ToString(exclude = "project") @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Region implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal index;

    @OneToMany
    private List<Project> project;

}