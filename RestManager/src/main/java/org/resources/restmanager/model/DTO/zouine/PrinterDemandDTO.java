package org.resources.restmanager.model.DTO.zouine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Printer;
import org.resources.restmanager.model.entities.Teacher;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrinterDemandDTO {
    private Printer printer;
    private List<Teacher> teachers;

    public static PrinterDemandDTO toDTO(Printer printer,List<Teacher> teachers){
        return PrinterDemandDTO.builder()
                .printer(printer)
                .teachers(teachers)
                .build();
    }
}
