package projetointegrador.model;


import lombok.Data;
import projetointegrador.model.enums.Command;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit")
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String newValue;

    @Enumerated(EnumType.STRING)
    private Command command;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private String entityName;



}
