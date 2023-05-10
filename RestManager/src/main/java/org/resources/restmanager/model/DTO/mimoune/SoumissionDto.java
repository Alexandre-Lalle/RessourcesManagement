package org.resources.restmanager.model.DTO.mimoune;

import lombok.*;
import org.resources.restmanager.model.entities.Soumission;


import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class SoumissionDto implements Serializable {
    private Long id;
    private int etat;
    private String marqueOrdinateur;
    private String marqueImprimante;

    private float prix;
    private Date dateGarentie;
    private Date dateLivraison;
    private FournisseurDto fournisseurDto;
    private OffreDto offreDto;

    public static SoumissionDto toDto(Soumission soumission) {
        return SoumissionDto.builder()
                .id(soumission.getId())
                .fournisseurDto(FournisseurDto.toDto(soumission.getFournisseurS()))
                .marqueOrdinateur(soumission.getMarqueOrdinateur())
                .marqueImprimante(soumission.getMarqueImprimante())
                .etat(soumission.getEtat())
                .dateGarentie(soumission.getDateGarentie())
                .dateLivraison(soumission.getDateLivraison())
                .offreDto(OffreDto.toDto(soumission.getOffre()))
                .prix(soumission.getPrix()).build();
    }

}
