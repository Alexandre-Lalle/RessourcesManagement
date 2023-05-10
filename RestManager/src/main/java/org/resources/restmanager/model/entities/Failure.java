package org.resources.restmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Failure {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Date date;
    private boolean processed;
    @JsonIgnore
    @ManyToOne
    private Resource resource;
    @JsonIgnore
    @ManyToOne
    private Teacher teacher;
    @JsonIgnore
    @OneToOne(mappedBy = "failure", cascade = CascadeType.ALL)
    private Report report;
}
