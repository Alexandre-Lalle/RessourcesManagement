package org.resources.restmanager.model.DTO.Old;

import lombok.*;
import org.resources.restmanager.model.entities.Computer;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder

public class OrdinateurDto implements Serializable {
  private long id;
    private String code;
    private Date dateLiv;
    private Date dateGarantie;
    private int etat;
    private boolean estPartager;
    private String marque;
    private String cpu;
    private String dd;
    private String ecran;

    private int ram;
    int qty;
    private FournisseurDto fournisseurDto;

    private EnseignantDto enseignantDto;
    public static OrdinateurDto toDto(Computer computer){
        return OrdinateurDto.builder().
            id(computer.getId())
        .code(computer.getBarCode())
                .dateLiv(computer.getDeliveryDate())
                .dateGarantie(computer.getWarrantyDate())
                .etat(computer.getState())
                .marque(computer.getBrand())
                .ecran(computer.getScreen())
                .cpu(computer.getCPU())
                .dd(computer.getDisk())
                .qty(1)
                .ram(computer.getRAM())
//                .fournisseurDto(FournisseurDto.toDto(ordinateur.getFournisseur()))
//                .enseignantDto(EnseignantDto.toDto(ordinateur.getEnseignant()))
                .build();
    }
    public static boolean equals(OrdinateurDto ordinateurDto, Computer computer){
        return (ordinateurDto.cpu.equals(computer.getCPU()) &&
                ordinateurDto.dd == computer.getDisk() &&
                ordinateurDto.ecran == computer.getScreen() &&
                ordinateurDto.ram == computer.getRAM()) ;
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
