package org.resources.restmanager.model.DTO.lalle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.DTO.mimoune.DepartementDto;
import org.resources.restmanager.model.entities.Teacher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EnseignantDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isChef;
    private String department;

    public static EnseignantDto toDto(Teacher teacher){
            return EnseignantDto.builder().
                    id(teacher.getId()).firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName()).email(teacher.getEmail())
                    .department(teacher.getDepartment())
                    .build();
    }

    public static List<EnseignantDto> toDtoList(List<Teacher> teachers){
        List<EnseignantDto> listEnseignant = new ArrayList<>();
        for(Teacher t : teachers){
            listEnseignant.add(toDto(t));
        }
        return listEnseignant;
    }

    public static Teacher toEntity(EnseignantDto enseignantDto) {
        Teacher enseignant = new Teacher();
        enseignant.setId(enseignantDto.getId());
        enseignant.setFirstName(enseignantDto.getFirstName());
        enseignant.setLastName(enseignantDto.getLastName());
        enseignant.setEmail(enseignantDto.getEmail());
        enseignant.setDepartment(enseignantDto.getDepartment());
        return enseignant;
    }

    public static List<Teacher> toEntityList(List<EnseignantDto> enseignantDtos) {
        List<Teacher> enseignants = new ArrayList<>();
        for (EnseignantDto enseignantDto : enseignantDtos) {
            enseignants.add(toEntity(enseignantDto));
        }
        return enseignants;
    }



}
