package org.resources.restmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Date date;
    @NonNull
    private String explication;
    @NonNull
    private String frequency;
    @NonNull
    @Column(name = "order_column")
    private String order;
    @Column(columnDefinition = "INT DEFAULT 0")
    private boolean seen;
    @OneToOne
    @JoinColumn(name = "failure_id")
    private Failure failure;
    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RepairRequest> repairRequests;
}
