package org.resources.restmanager.model.DTO.mouhsine;

import lombok.*;
import org.resources.restmanager.model.entities.Soumission;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class SoumissionDTO implements Serializable {
    private Long id;
    private int etat;
    private String marqueOrdinateur;
    private String marqueImprimante;
    private Date dateGarentie;
    private Date dateLivraison;
    private float prix;
    //private FournisseurDto fournisseurDto;
    //private OffreDTO offreDto;

    public static SoumissionDTO toDto(Soumission soumission) {
        return SoumissionDTO.builder()
                .id(soumission.getId())
                .marqueOrdinateur(soumission.getMarqueOrdinateur())
                .marqueImprimante(soumission.getMarqueImprimante())
                .etat(soumission.getEtat())
                .dateGarentie(soumission.getDateGarentie())
                .dateLivraison(soumission.getDateLivraison())
                .prix(soumission.getPrix())
                .build();
                //.fournisseurDto(FournisseurDto.toDto(soumission.getFournisseurS()))
                //.offreDto(OffreDTO.toDto(soumission.getOffre()))
    }

    public static Soumission toEntity(SoumissionDTO soumissionDto) {
        return Soumission.builder()
                .id(soumissionDto.getId())
                .marqueOrdinateur(soumissionDto.getMarqueOrdinateur())
                .marqueImprimante(soumissionDto.getMarqueImprimante())
                .dateGarentie(soumissionDto.getDateGarentie())
                .dateLivraison(soumissionDto.getDateLivraison())
                .prix(soumissionDto.getPrix())
                .etat(soumissionDto.getEtat())
                //.fournisseurS(new Provider(soumissionDto.getFournisseurDto().getId()))
                //.offre(new Offre(soumissionDto.getOffreDto().getId()))
                .build();
    }
    public  static List<Soumission> toEntityList(List<SoumissionDTO> soumissionDTOList){
        List<Soumission> soumissionList = new ArrayList<>();
        for (SoumissionDTO soumissionDTO : soumissionDTOList){
            soumissionList.add(toEntity(soumissionDTO));
        }
        return soumissionList;
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
