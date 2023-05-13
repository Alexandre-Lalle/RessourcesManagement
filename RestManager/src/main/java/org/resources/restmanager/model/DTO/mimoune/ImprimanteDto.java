package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Printer;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImprimanteDto implements Serializable {
    private long id;
    private String barCode;
    private String resolution;
    private String brand ;
    private int printSpeed;
    private Date dateOfRequest;
    private Date deliveryDate;
    private Date warrantyDate;
    private Date assignmentDate;
    int qty;
    int state ;
    private FournisseurDto fournisseurDto;
    private EnseignantDto enseignantDto;
    List<PanneDto> failureDtoList ;
    public static ImprimanteDto toDto(Printer printer){

        ImprimanteDto imprimanteDto =  ImprimanteDto.builder().
                id(printer.getId())
                .resolution(printer.getResolution())
                .printSpeed(printer.getPrintSpeed())
                .qty(1)
                .enseignantDto(EnseignantDto.toDto(printer.getTeachers().get(0)))
                .state(printer.getState())
                .build();
        List<PanneDto> panneDtoList = new ArrayList<>();
        printer.getFailures().forEach( failure -> {
            System.err.println(failure.getId());
            panneDtoList.add(PanneDto.toDto(failure));
        });
       imprimanteDto.setFailureDtoList(panneDtoList);
        return  imprimanteDto;
    }
    public static boolean equals(ImprimanteDto imprimanteDto, Printer printer){
        return (imprimanteDto.resolution.equals(printer.getResolution()) &&
                imprimanteDto.printSpeed == printer.getPrintSpeed() )
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
