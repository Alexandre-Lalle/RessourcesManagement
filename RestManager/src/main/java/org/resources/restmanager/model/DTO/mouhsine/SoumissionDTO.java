package org.resources.restmanager.model.DTO.mouhsine;

import lombok.*;
import org.resources.restmanager.model.entities.Soumission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class SoumissionDTO implements Serializable {
    private Long id;
    private int etat;
    private String marque;
    private float prix;
    //private FournisseurDto fournisseurDto;
    //private OffreDTO offreDto;

    public static SoumissionDTO toDto(Soumission soumission) {
        return SoumissionDTO.builder()
                .id(soumission.getId())
                .marque(soumission.getMarque())
                .etat(soumission.getEtat())
                .prix(soumission.getPrix())
                .build();
                //.fournisseurDto(FournisseurDto.toDto(soumission.getFournisseurS()))
                //.offreDto(OffreDTO.toDto(soumission.getOffre()))
    }
    public static List<SoumissionDTO> toDtoList(List<Soumission> soumissionList){
        List<SoumissionDTO> soumissionDTOList = new ArrayList<>();
        if(soumissionList == null)
            soumissionList = new ArrayList<>();
        for(Soumission soumission : soumissionList){
            soumissionDTOList.add(toDto(soumission));
        }
        return soumissionDTOList;
    }

}
