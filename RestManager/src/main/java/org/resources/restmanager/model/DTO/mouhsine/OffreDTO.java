package org.resources.restmanager.model.DTO.mouhsine;


import lombok.*;
import org.resources.restmanager.model.entities.Offre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class OffreDTO implements Serializable {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private List<OrdinateurDTO> ordinateurDtoList;
    private List<ImprimanteDTO> imprimanteDtoList;
    private List<SoumissionDTO> soumissionDTOList;

    public static OffreDTO toDto(Offre offre) {
        return OffreDTO.builder()
                .id(offre.getId())
                .dateDebut(offre.getDateDebut())
                .dateFin(offre.getDateFin())
                .ordinateurDtoList(OrdinateurDTO.toDtoList(offre.getCmputers()))
                .imprimanteDtoList(ImprimanteDTO.toDtoList(offre.getPrinters()))
                .soumissionDTOList(SoumissionDTO.toDtoList(offre.getSoumissionList()))
                .build();
    }
    public static Offre toEntity(OffreDTO offreDto) {
        System.out.println("toEtitie getResourceDTOList: "+ offreDto.retournerResourceDTOList());
        return Offre.builder()
                .id(offreDto.getId())
                .dateDebut(offreDto.getDateDebut())
                .dateFin(offreDto.getDateFin())
                .resourceList(ResourceDTO.toEntitieList(offreDto.retournerResourceDTOList()))
                .soumissionList(SoumissionDTO.toEntityList(offreDto.getSoumissionDTOList()))
                .build();
    }

    public List<ResourceDTO> retournerResourceDTOList(){
        List<ResourceDTO> resourcesDTO = new ArrayList<>() ;
        for (OrdinateurDTO or : ordinateurDtoList){
            resourcesDTO.add(or);
        }
        for (ImprimanteDTO im : imprimanteDtoList){
            resourcesDTO.add(im);
        }
        return resourcesDTO;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<OrdinateurDTO> getOrdinateurDtoList() {
        return ordinateurDtoList;
    }

    public void setOrdinateurDtoList(List<OrdinateurDTO> ordinateurDtoList) {
        this.ordinateurDtoList = ordinateurDtoList;
    }

    public List<ImprimanteDTO> getImprimanteDtoList() {
        return imprimanteDtoList;
    }

    public void setImprimanteDtoList(List<ImprimanteDTO> imprimanteDtoList) {
        this.imprimanteDtoList = imprimanteDtoList;
    }

    public List<SoumissionDTO> getSoumissionDTOList() {
        return soumissionDTOList;
    }

    public void setSoumissionDTOList(List<SoumissionDTO> soumissionDTOList) {
        this.soumissionDTOList = soumissionDTOList;
    }
}
