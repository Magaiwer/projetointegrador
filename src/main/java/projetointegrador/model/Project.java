package projetointegrador.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import projetointegrador.listeners.AuditListeners;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditListeners.class)
@Entity
@Table(name = "project")
@Data
@ToString(exclude = {"person", "rooms"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class Project implements Serializable {

    @EqualsAndHashCode.Include
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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    public String getIdProjectAndName() {
        return this.getId() + " - " + this.getName();
    }

    public boolean isNew() {
        return this.id == null;
    }

}
