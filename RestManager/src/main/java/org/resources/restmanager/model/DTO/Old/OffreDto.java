package org.resources.restmanager.model.DTO.Old;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Offre;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class OffreDto implements Serializable {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private List<OrdinateurDto> ordinateurDtoList;
    private List<ImprimanteDto> imprimanteDtoList;
    public static OffreDto toDto(Offre offre) {
        return OffreDto.builder()
                .id(offre.getId())
                .dateDebut(offre.getDateDebut())
                .dateFin(offre.getDateFin())
                .build();

    }

}
