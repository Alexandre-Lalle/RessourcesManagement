package org.resources.restmanager.model.DTO.zouine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Teacher;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemandDTO {
    private Resource resource;
    private List<Teacher> teachers;

    public static DemandDTO toDTO(Resource rsc, List<Teacher> teachers){
        return DemandDTO.builder()
                .resource(rsc)
                .teachers(teachers)
                .build();
    }
}
