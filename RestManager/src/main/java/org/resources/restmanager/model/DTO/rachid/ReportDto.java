package org.resources.restmanager.model.DTO.rachid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Report;
import org.springframework.lang.NonNull;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportDto {
    private Long id;
    private Date date;
    private String explication;
    private String frequency;
    private String order;
    private String resourceType;
    private String teacherName;
    private String teacherEmail;
    private String teacherDep;
    private String fournisseurName;
    private String fournisseurManager;
    private Long fournisseurId;
    private Long resourceId;

    public static ReportDto toDto(@NonNull Report report){
        return ReportDto.builder()
                .id(report.getId())
                .date(report.getDate())
                .explication(report.getExplication())
                .frequency(report.getFrequency())
                .order(report.getOrder())
                .resourceType(report.getFailure().getResource().getResourceType())
                .teacherEmail(report.getFailure().getTeacher().getEmail())
                .teacherName(report.getFailure().getTeacher().getLastName())
                .teacherDep(report.getFailure().getTeacher().getDepartment())
                .fournisseurManager(report.getFailure().getResource().getProvider().getManager())
                .fournisseurName(report.getFailure().getResource().getProvider().getName())
                .fournisseurId(report.getFailure().getResource().getProvider().getId())
                .resourceId(report.getFailure().getResource().getId())
                .build();
    }

}
