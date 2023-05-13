package org.resources.restmanager.model.DTO.Old;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Printer;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImprimanteDto implements Serializable {
    private long id;
    private String code;
    private Date dateLiv;
    private Date dateGarantie;
    private int etat;
    private String marque;
    private String resolution;
    private int vitesse;
    int qty;

    private FournisseurDto fournisseurDto;

    private EnseignantDto enseignantDto;
    public static ImprimanteDto toDto(Printer printer){
        return ImprimanteDto.builder().
                id(printer.getId())
                .code(printer.getBarCode())
                .dateLiv(printer.getAssignmentDate())
                .dateGarantie(printer.getWarrantyDate())
                .etat(printer.getState())
                .marque(printer.getBrand())
                .resolution(printer.getResolution())
                .vitesse(printer.getPrintSpeed())
                .qty(1)
                .enseignantDto(EnseignantDto.toDto(printer.getTeachers().get(0)))
                .build();
    }
    public static boolean equals(ImprimanteDto imprimanteDto, Printer printer){
        return (imprimanteDto.resolution == printer.getResolution()&&
                imprimanteDto.vitesse == printer.getPrintSpeed() )
                 ;
    }
//    public static ImprimanteDto toDto(Imprimante imprimante){
//        return ImprimanteDto.builder()
//                .etat(imprimante.getEtat())
//                .estPartager(imprimante.isEstPartager())
//                .marque(imprimante.getMarque())
//                .enseignantDto(EnseignantDto.toDto(imprimante.getEnseignant()))
//                .build();
//    }


}
