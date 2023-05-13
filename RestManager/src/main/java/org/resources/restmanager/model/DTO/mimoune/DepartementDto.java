package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartementDto {
    private Long id;
    private String nom;


}
