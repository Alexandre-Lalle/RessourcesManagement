package org.resources.restmanager.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.DTO.rachid.DemandeRetourDto;
import org.resources.restmanager.model.entities.Resource;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DemandeRetour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String message;

    @OneToOne
    @JoinColumn(name = "resource_id", insertable = true, updatable = false)
    private Resource resource;


    public static DemandeRetour toEntity(DemandeRetourDto demandeRetour, Resource resource) {
        return DemandeRetour.builder().message(demandeRetour.getMessage()).resource(resource).build();
    }
}
