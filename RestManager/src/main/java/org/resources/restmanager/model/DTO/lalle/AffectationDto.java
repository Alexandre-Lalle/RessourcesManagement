package org.resources.restmanager.model.DTO.lalle;

import lombok.*;
import org.resources.restmanager.model.entities.Affectation;
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

    private RessourceDto resource;

    public static AffectationDto toDto(Affectation affectation){
        return AffectationDto.builder().
                id(affectation.getId())
                .dateAffectation(affectation.getDateAffectation())
                .teacherList(toDtoList(affectation.getTeacherList()))
                .resource(RessourceDto.toDto(affectation.getResource())).build();
    }

    public static Affectation toEntity(AffectationDto affectationDto) {
        Affectation affectation = new Affectation();
        affectation.setId(affectationDto.getId());
        affectation.setDateAffectation(affectationDto.getDateAffectation());
        affectation.setTeacherList(toEntityList(affectationDto.getTeacherList()));
        affectation.setResource(RessourceDto.toEntity(affectationDto.getResource()));
        return affectation;
    }




}
