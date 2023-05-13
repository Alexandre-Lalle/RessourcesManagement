package org.resources.restmanager.model.DTO.lalle;

import lombok.*;
import org.resources.restmanager.model.entities.Affectation;
import org.resources.restmanager.model.entities.Computer;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Teacher;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import static org.resources.restmanager.model.DTO.lalle.EnseignantDto.toDtoList;
import static org.resources.restmanager.model.DTO.lalle.EnseignantDto.toEntityList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class AffectationDto implements Serializable {

    private Long id;

    private Date dateAffectation;

    private List<EnseignantDto> teacherList;

    private Long resourceId;

    public static AffectationDto toDto(Affectation affectation){
        return AffectationDto.builder().
                id(affectation.getId())
                .dateAffectation(affectation.getDateAffectation())
                .teacherList(toDtoList(affectation.getTeacherList()))
                .resourceId(affectation.getResource().getId()).build();
    }

    public static Affectation toEntity(AffectationDto affectationDto, Resource resource) {
        Affectation affectation = new Affectation();
        affectation.setId(affectationDto.getId());
        affectation.setDateAffectation(affectationDto.getDateAffectation());
        affectation.setTeacherList(toEntityList(affectationDto.getTeacherList()));
        affectation.setResource(resource);
        return affectation;
    }




}
