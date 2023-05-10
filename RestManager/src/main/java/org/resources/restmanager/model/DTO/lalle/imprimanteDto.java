package org.resources.restmanager.model.DTO.lalle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Printer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class imprimanteDto implements Serializable {
    private long id;
    private String barCode;
    private Date dateOfRequest;
    private Date deliveryDate;
    private Date warrantyDate;
    private int state;
    private String brand;
    private String resolution;
    private int printSpeed;
    private String providerName;

    public static imprimanteDto toDto(Printer printer){
        return imprimanteDto.builder().
                id(printer.getId())
                .barCode(printer.getBarCode())
                .dateOfRequest(printer.getDateOfRequest())
                .deliveryDate(printer.getDeliveryDate())
                .warrantyDate(printer.getWarrantyDate())
                .state(printer.getState())
                .brand(printer.getBrand())
                .resolution(printer.getResolution())
                .printSpeed(printer.getPrintSpeed())
                .providerName(printer.getProviderName())
                .build();
    }

    public static List<imprimanteDto> toDtoList(List<Printer> printerList) {
        List<imprimanteDto> list = new ArrayList<>();
        for (Printer p : printerList) {
            list.add(toDto(p));
        }
        return list;
    }

    public String getResourceType(){return "printer" ; }

    public static boolean equals(imprimanteDto imprimanteDto, Printer printer){
        return (imprimanteDto.resolution == printer.getResolution()&&
                imprimanteDto.printSpeed == printer.getPrintSpeed() )
                 ;
    }

}
