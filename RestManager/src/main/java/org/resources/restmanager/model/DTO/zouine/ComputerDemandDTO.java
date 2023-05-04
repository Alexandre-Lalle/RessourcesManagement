package org.resources.restmanager.model.DTO.zouine;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Computer;
import org.resources.restmanager.model.entities.Teacher;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComputerDemandDTO {
    private Computer computer;
    private List<Teacher> teachers;

    public static ComputerDemandDTO toDTO(Computer computer, List<Teacher> teachers){
        return ComputerDemandDTO.builder()
                .computer(computer)
                .teachers(teachers)
                .build();
    }
}
