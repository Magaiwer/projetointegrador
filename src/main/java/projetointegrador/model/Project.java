package projetointegrador.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project")
@Data
@ToString(exclude = "person")
@EqualsAndHashCode(exclude = "person")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany
    private List<Room> rooms;




}
