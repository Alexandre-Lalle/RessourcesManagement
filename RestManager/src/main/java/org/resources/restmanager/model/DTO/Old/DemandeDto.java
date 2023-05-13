package org.resources.restmanager.model.DTO.Old;
import lombok.*;
import org.resources.restmanager.model.entities.Notification;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class DemandeDto {

    private Long id;

    private String motif;
    private String contenu;

    String departementd;
    public static DemandeDto toDto(Notification notification) {
        return DemandeDto.builder().
                id(notification.getId())
                .motif(notification.getSubject())
                .contenu(notification.getContent())
                .build();
    }
}
