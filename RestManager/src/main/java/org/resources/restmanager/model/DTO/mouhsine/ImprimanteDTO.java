package org.resources.restmanager.model.DTO.mouhsine;


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

public class ImprimanteDTO extends ResourceDTO implements Serializable {
    private long id;
    private String code;
    private Date dateLiv;
    private Date dateGarantie;
    private int etat;
    private String marque;
    private String resolution;
    private int vitesse;
    private boolean selected = false ;
    public static ImprimanteDTO toDto(Printer printer) {
        return ImprimanteDTO.builder().
                id(printer.getId())
                .code(printer.getBarCode())
                .dateLiv(printer.getAssignmentDate())
                .dateGarantie(printer.getWarrantyDate())
                .etat(printer.getState())
                .marque(printer.getBrand())
                .resolution(printer.getResolution())
                .vitesse(printer.getPrintSpeed())
                .build();
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

    public static List<ImprimanteDTO> toDtoList(List<Printer> printerList) {
        List<ImprimanteDTO> list = new ArrayList<>();
        for (Printer printer : printerList) {
            list.add(toDto(printer));
        }
        return list;
    }

    public String getResourceType() { return "Printer";}
}
