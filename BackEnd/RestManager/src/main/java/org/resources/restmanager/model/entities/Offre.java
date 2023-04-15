package org.resources.restmanager.model.entities;

import lombok.*;

import jakarta.persistence.*;
import org.resources.restmanager.model.DTO.mimoune.OffreDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateDebut;
    private Date dateFin;

    @OneToMany(mappedBy = "offre",fetch = FetchType.LAZY)
    private List<Resource> resourceList;
    @ManyToOne
    private Responsable responsable;
   @OneToMany(mappedBy = "offre")
    private List<Soumission> soumissionList;
    public static Offre toEntity(OffreDto offreDto) {
        return Offre.builder()
                .id(offreDto.getId())
                .dateDebut(offreDto.getDateDebut())
                .dateFin(offreDto.getDateFin())
                .build();
    }

    public Offre(Long id) {
        this.id = id;
    }
}
