package org.resources.restmanager.model.DTO.Old;

import lombok.*;
import org.resources.restmanager.model.entities.Failure;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class PanneDto implements Serializable {
    private Long id;
    private String explication;
    private Date dateApp;
    //private String frequence;
    //private String ordre;
    private Boolean traiter;

    private RessourceDto ressourceDto;

    private EnseignantDto enseignantDto;

    private ConstatDto constatDto;

    public static PanneDto toDto(Failure failure){
        return PanneDto.builder()
                .id(failure.getId())
                .dateApp(failure.getDate())
                .traiter(failure.isProcessed())
                .build();

    }



}
