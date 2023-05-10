package org.resources.restmanager.model.DTO.Old;

import lombok.*;
import org.resources.restmanager.model.entities.Soumission;


import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class SoumissionDto implements Serializable {
    private Long id;
    private int etat;
    private String marque;
    private float prix;
    private FournisseurDto fournisseurDto;
    private OffreDto offreDto;

//    public static SoumissionDto toDto(Soumission soumission) {
//        return SoumissionDto.builder()
//                .id(soumission.getId())
//                .fournisseurDto(FournisseurDto.toDto(soumission.getFournisseurS()))
//                .marque(soumission.getMarque())
//                .etat(soumission.getEtat())
//                .offreDto(OffreDto.toDto(soumission.getOffre()))
//                .prix(soumission.getPrix()).build();
//    }

}
