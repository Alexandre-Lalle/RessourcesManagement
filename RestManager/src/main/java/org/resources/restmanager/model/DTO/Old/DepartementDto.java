package org.resources.restmanager.model.DTO.Old;

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
