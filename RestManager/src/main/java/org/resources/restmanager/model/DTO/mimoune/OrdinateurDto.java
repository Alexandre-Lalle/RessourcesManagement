package org.resources.restmanager.model.DTO.mimoune;

import lombok.*;
import org.resources.restmanager.model.entities.Computer;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder

public class OrdinateurDto implements Serializable {
  private long id;
  private String barCode;
  private String brand;
  private String CPU;
  private String disk;
  private int RAM;
  private String screen;
  private int state;
  private Date dateOfRequest;
  private Date deliveryDate;
  private Date warrantyDate;
  private Date assignmentDate;

    int qty;
    private FournisseurDto fournisseurDto;

    private EnseignantDto enseignantDto;
    public static OrdinateurDto toDto(Computer computer){
        return OrdinateurDto.builder().
            id(computer.getId())
                .screen(computer.getScreen())
                .CPU(computer.getCPU())
                .disk(computer.getDisk())
                .qty(1)
                .RAM(computer.getRAM())
                .state(computer.getState())
//                .fournisseurDto(FournisseurDto.toDto(ordinateur.getFournisseur()))
//                .enseignantDto(EnseignantDto.toDto(ordinateur.getEnseignant()))
                .build();
    }
    public static boolean equals(OrdinateurDto ordinateurDto, Computer computer){
        return (ordinateurDto.CPU.equals(computer.getCPU()) &&
                ordinateurDto.disk.equals(computer.getDisk()) &&
                ordinateurDto.screen.equals(computer.getScreen())  &&
                ordinateurDto.RAM == computer.getRAM()) ;
    }
//public static OrdinateurDto toDto(Ordinateur ordinateur){
//    return OrdinateurDto.builder()
//            .etat(ordinateur.getEtat())
//            .estPartager(ordinateur.isEstPartager())
//            .ram(ordinateur.getRam())
//            .enseignantDto(EnseignantDto.toDto(ordinateur.getEnseignant()))
//            .build();
//}
}
