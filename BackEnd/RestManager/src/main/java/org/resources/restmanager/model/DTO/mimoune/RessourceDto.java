package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Resource;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RessourceDto implements Serializable {
    private long id ;
    private String code;
    private Date dateLiv;
    private Date dateGarantie;
    private int etat;
    private String marque;
    private FournisseurDto fournisseurDto;
    int qty;

//    private EnseignantDto enseignantDto;
    public static RessourceDto toDto(Resource resource){
        return RessourceDto.builder().
                id(resource.getId())
                .code(resource.getBarCode())
                .dateLiv(resource.getDeliveryDate())
                .dateGarantie(resource.getWarrantyDate())
                .etat(resource.getState())
                .fournisseurDto(FournisseurDto.toDto(resource.getProvider()))
//                .enseignantDto(EnseignantDto.toDto(resource.getTeachers().get(0)))
                .build();
    }
}
