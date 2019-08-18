package projetointegrador.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "form")
@Data
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean audited;

    @ManyToOne(fetch = FetchType.LAZY)
    private Entities entity;
}
