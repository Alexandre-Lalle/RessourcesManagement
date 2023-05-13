package org.resources.restmanager.model.DTO.zouine;

import lombok.*;
import org.resources.restmanager.model.entities.Failure;

import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FailureDTO {
    private Long id;
    private Date date;
    private String resourceType;
    private String resourceBrand;
    private String teacherName;
    private String teacherEmail;
    private boolean processed;

    public static FailureDTO toDTO(@NonNull Failure failure){
        return FailureDTO.builder()
                .id(failure.getId())
                .date(failure.getDate())
                .resourceType(failure.getResource().getResourceType())
                .resourceBrand(failure.getResource().getBrand())
                .teacherName(failure.getTeacher().getFirstName()+" "+failure.getTeacher().getLastName())
                .teacherEmail(failure.getTeacher().getEmail())
                .processed(failure.isProcessed())
                .build();
    }
}
