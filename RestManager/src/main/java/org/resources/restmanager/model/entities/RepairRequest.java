package org.resources.restmanager.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairRequest {
    @Id
    private Long id;
    @NonNull
    private String message;
    @ManyToOne
    private Report report;
}
