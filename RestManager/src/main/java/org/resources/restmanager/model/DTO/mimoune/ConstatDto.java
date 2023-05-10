package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConstatDto  implements Serializable {
    private Long idC;
    private String constat;
    private Long idP;
    private boolean changer;
    private boolean traiter;

}
