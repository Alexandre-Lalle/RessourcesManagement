package org.resources.restmanager.model.entities;

import lombok.*;

import jakarta.persistence.*;
import org.resources.restmanager.model.DTO.mouhsine.OffreDTO;
import org.resources.restmanager.model.DTO.mouhsine.ResourceDTO;
import org.resources.restmanager.model.DTO.mouhsine.SoumissionDTO;

import java.io.Serializable;
import java.util.ArrayList;
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
    public static Offre toEntity(OffreDTO offreDto) {
        return Offre.builder()
                .id(offreDto.getId())
                .dateDebut(offreDto.getDateDebut())
                .dateFin(offreDto.getDateFin())
                .build();
    }

    public static Offre toEntity2(OffreDTO offreDto) {
        System.out.println("toEtitie getResourceDTOList: "+ offreDto.retournerResourceDTOList());
        return Offre.builder()
                .id(offreDto.getId())
                .dateDebut(offreDto.getDateDebut())
                .dateFin(offreDto.getDateFin())
                .resourceList(ResourceDTO.toEntitieList(offreDto.retournerResourceDTOList()))
                .soumissionList(SoumissionDTO.toEntityList(offreDto.getSoumissionDTOList()))
                .build();
    }

    public Offre(Long id) {
        this.id = id;
    }


    public List<Computer> getCmputers(){
        List<Computer> computers = new ArrayList<>() ;
        for (Resource resource : resourceList){
            if( resource.getResourceType() == "Computer"){
                computers.add((Computer) resource);
            }
        }
        return computers;
    }

    public List<Printer> getPrinters(){
        List<Printer> printers = new ArrayList<>() ;
        for (Resource resource : resourceList){
            if( resource.getResourceType() == "Printer"){
                printers.add((Printer) resource);
            }
        }
        return printers;
    }
}
