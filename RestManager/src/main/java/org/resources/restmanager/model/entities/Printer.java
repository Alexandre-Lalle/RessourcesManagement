package org.resources.restmanager.model.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.resources.restmanager.model.DTO.mouhsine.ImprimanteDTO;
import org.springframework.lang.NonNull;

@Entity
@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Printer extends Resource {
    @NonNull
    private String resolution;
    @NonNull
    private int printSpeed;

    public Printer(String resolution, int printSpeed){
        this.resolution = resolution;
        this.printSpeed = printSpeed;
    }

    @Override
    public String getResourceType(){
        return "Printer";
    }


    public static Printer toEntity(ImprimanteDTO imprimanteDTO) {

        System.out.println("Printer toEntity ::: => "+imprimanteDTO);
        Printer printer = new Printer();
        printer.setResolution(imprimanteDTO.getResolution());
        printer.setPrintSpeed(imprimanteDTO.getVitesse());
        printer.setId(imprimanteDTO.getId());
        printer.setBarCode(imprimanteDTO.getCode());
        printer.setAssignmentDate(imprimanteDTO.getDateGarantie());
        printer.setDeliveryDate(imprimanteDTO.getDateLiv());
        printer.setBrand(imprimanteDTO.getMarque());
        printer.setState(imprimanteDTO.getEtat());
        return printer;
    }
}
