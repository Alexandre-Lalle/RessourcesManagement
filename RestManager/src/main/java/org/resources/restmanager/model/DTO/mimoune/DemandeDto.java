package org.resources.restmanager.model.DTO.mimoune;
import lombok.*;
import org.resources.restmanager.model.entities.Notification;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class DemandeDto {

    private Long id;
    private Date date ;
    private String subject;
    private String content ;
    String departementd;
    public static DemandeDto toDto(Notification notification) {
        return DemandeDto.builder().
                id(notification.getId())
                .date(notification.getDate())
                .subject(notification.getSubject())
                .content(notification.getContent())
                .build();
    }
}
