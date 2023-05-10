package org.resources.restmanager.model.DTO.mimoune;

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
    private Date date;

    private Boolean state;

    private RessourceDto ressourceDto;

    private EnseignantDto enseignantDto;

    private ConstatDto constatDto;

    public static PanneDto toDto(Failure failure){
        return PanneDto.builder()
                .id(failure.getId())
                .date(failure.getDate())
                .state(failure.isProcessed())
                .build();

    }



}
