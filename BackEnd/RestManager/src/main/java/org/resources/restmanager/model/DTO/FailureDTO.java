package org.resources.restmanager.model.DTO;

import lombok.*;
import org.resources.restmanager.model.entities.Failure;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Teacher;

import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FailureDTO {
    private Long id;
    private Date date;
    private String resourceType;
    private String resourceName;
    private String teacherName;
    private String teacherEmail;
    private boolean state;

    public static FailureDTO toDTO(@NonNull Failure failure){
        return FailureDTO.builder()
                .id(failure.getId())
                .date(failure.getDate())
                .resourceType(failure.getResource().getResourceType())
                .resourceName(failure.getResource().getName())
                .teacherName(failure.getTeacher().getFirstName()+" "+failure.getTeacher().getLastName())
                .teacherEmail(failure.getTeacher().getEmail())
                .state(failure.getReport()!=null)
                .build();
    }
}
