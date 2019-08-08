package projetointegrador.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
}
