package org.resources.restmanager.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.resources.restmanager.model.DTO.mouhsine.OrdinateurDTO;
import org.springframework.lang.NonNull;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Computer extends Resource {
    @NonNull
    private String CPU;
    @NonNull
    private String disk;
    @NonNull
    private int RAM;
    @NonNull
    private String screen;


    @Override
    public String getResourceType(){
        return "Computer";
    }

    public static Computer toEntity(OrdinateurDTO ordinateurDTO) {
        Computer computer = new Computer();
        computer.setCPU(ordinateurDTO.getCpu());
        computer.setDisk(ordinateurDTO.getDisk());
        computer.setRAM(ordinateurDTO.getRam());
        computer.setScreen(ordinateurDTO.getScreen());
        computer.setId(ordinateurDTO.getId());
        computer.setBarCode(ordinateurDTO.getCode());
        computer.setAssignmentDate(ordinateurDTO.getDateGarantie());
        computer.setDeliveryDate(ordinateurDTO.getDateLiv());
        computer.setBrand(ordinateurDTO.getMarque());
        computer.setState(ordinateurDTO.getEtat());
        return computer;
    }
}
