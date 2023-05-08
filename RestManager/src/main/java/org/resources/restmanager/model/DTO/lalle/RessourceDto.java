package org.resources.restmanager.model.DTO.lalle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.DTO.mimoune.FournisseurDto;
import org.resources.restmanager.model.entities.Resource;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RessourceDto implements Serializable {
    private long id;
    private String barCode;
    private Date dateOfRequest;
    private Date deliveryDate;
    private Date warrantyDate;
    private int state;
    private String brand;

    public static RessourceDto toDto(Resource resource){
        return RessourceDto.builder().
                id(resource.getId())
                .barCode(resource.getBarCode())
                .dateOfRequest(resource.getDateOfRequest())
                .deliveryDate(resource.getDeliveryDate())
                .warrantyDate(resource.getWarrantyDate())
                .state(resource.getState())
                .brand(resource.getBrand())
                .build();
    }

    public static Resource toEntity(RessourceDto ressourceDto) {
        Resource resource = null;
        resource.setId(ressourceDto.getId());
        resource.setBarCode(ressourceDto.getBarCode());
        resource.setDateOfRequest(ressourceDto.getDateOfRequest());
        resource.setDeliveryDate(ressourceDto.getDeliveryDate());
        resource.setWarrantyDate(ressourceDto.getWarrantyDate());
        resource.setState(ressourceDto.getState());
        resource.setBrand(ressourceDto.getBrand());
        return resource;
    }
}
