package org.resources.restmanager.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.resources.restmanager.model.DTO.mimoune.SoumissionDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@SuperBuilder
public class Soumission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marque;

    private Float prix;
    private int etat;
    @ManyToOne
    private Offre offre;
    @ManyToOne
    private Provider fournisseurS;
    public static Soumission toEntity(SoumissionDto soumissionDto) {
        return Soumission.builder()
                .marque(soumissionDto.getMarque())
                .fournisseurS(new Provider(soumissionDto.getFournisseurDto().getId()))
                .prix(soumissionDto.getPrix())
                .offre(new Offre(soumissionDto.getOffreDto().getId()))
                .build();
    }
}
