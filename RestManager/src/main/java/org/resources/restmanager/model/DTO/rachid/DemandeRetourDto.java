package org.resources.restmanager.model.DTO.rachid;

import jakarta.persistence.Entity;
import lombok.*;
import org.resources.restmanager.model.entities.DemandeRetour;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DemandeRetourDto {

    private String message;
    private Long resource_id;
    public static DemandeRetourDto toDto(DemandeRetour demandeRetour){
        return DemandeRetourDto.builder().message(demandeRetour.getMessage()).resource_id(demandeRetour.getId()).build();  }

}
