package org.resources.restmanager.model.DTO.mimoune;

import lombok.*;
import org.resources.restmanager.model.entities.DemandeRetour;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class DemandeRetourDto {
    String message;
    ImprimanteDto imprimanteDto;
    OrdinateurDto ordinateurDto ;
   public  static DemandeRetourDto toDto(DemandeRetour demandeRetour){
          return   DemandeRetourDto.builder().message(demandeRetour.getMessage()).build();
    }

}
