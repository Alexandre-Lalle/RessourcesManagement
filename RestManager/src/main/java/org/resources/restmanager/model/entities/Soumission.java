package org.resources.restmanager.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.resources.restmanager.model.DTO.mimoune.SoumissionDto;
import org.resources.restmanager.model.DTO.mouhsine.SoumissionDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String marqueOrdinateur;
    private String marqueImprimante;
    private Float prix;
    private int etat;
    private Date dateGarentie;
    private Date dateLivraison;
    @ManyToOne
    private Offre offre;
    @ManyToOne
    private Provider fournisseurS;
    public static Soumission toEntity(SoumissionDto soumissionDto) {
        return Soumission.builder()
                .marqueOrdinateur(soumissionDto.getMarqueOrdinateur())
                .marqueImprimante(soumissionDto.getMarqueImprimante())
                .fournisseurS(new Provider(soumissionDto.getFournisseurDto().getId()))
                .prix(soumissionDto.getPrix())
                .dateGarentie(soumissionDto.getDateGarentie())
                .dateLivraison(soumissionDto.getDateLivraison())
                .offre(new Offre(soumissionDto.getOffreDto().getId()))
                .build();
    }

}
