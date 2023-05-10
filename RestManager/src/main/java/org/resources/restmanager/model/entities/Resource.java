package org.resources.restmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Resource {
    @Id
    @GeneratedValue
    private Long id;
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name= "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String barCode;
    @CreationTimestamp
    private Date dateOfRequest;
    private Date deliveryDate;
    private Date warrantyDate;
    private Date assignmentDate;
    private String brand;
    @Column(columnDefinition = "INT DEFAULT -1")
    private int state;
    @ManyToOne
    private Provider provider;
    @ManyToMany
    @JoinTable(
            name = "teacher_resource",
            joinColumns = {@JoinColumn(name = "resource_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    @JsonIgnore
    private List<Teacher> teachers;
    @JsonIgnore
    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private List<Failure> failures;
    @ManyToOne
    @JsonIgnore
    private Offre offre;


    @PrePersist
    public void setDefaults() {
        if (state == 0) {
            state = -1;
        }
    }

    public String getResourceType(){
        return "Resource";
    };

    public String getProviderName() {
        if (provider == null) {
            return "No provider";
        } else {
            return provider.getName();
        }
    }
}
