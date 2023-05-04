package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Teacher;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EnseignantDto implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private boolean isChef;
    private DepartementDto departementDto;
    public static EnseignantDto toDto(Teacher teacher){
            return EnseignantDto.builder().
                    id(teacher.getId())
                    .build();
    }

}
