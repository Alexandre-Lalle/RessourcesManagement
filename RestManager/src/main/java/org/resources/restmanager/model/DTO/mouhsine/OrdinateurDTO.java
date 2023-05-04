package org.resources.restmanager.model.DTO.mouhsine;


import lombok.*;
import org.resources.restmanager.model.entities.Computer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder

public class OrdinateurDTO extends ResourceDTO implements Serializable {
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
    private boolean selected = false;
//    private FournisseurDto fournisseurDto;
//    private EnseignantDto enseignantDto;

    public static OrdinateurDTO toDto(Computer computer) {
        return OrdinateurDTO.builder().
                id(computer.getId())
                .code(computer.getBarCode())
                .dateLiv(computer.getDeliveryDate())
                .dateGarantie(computer.getWarrantyDate())
                .etat(computer.getState())
                .marque(computer.getBrand())
                .ecran(computer.getScreen())
                .cpu(computer.getCPU())
                .dd(computer.getDisk())
                .ram(computer.getRAM())
                .build();
    }

    public static List<OrdinateurDTO> toDtoList(List<Computer> computerList) {
        List<OrdinateurDTO> list = new ArrayList<>();
        for (Computer computer : computerList) {
            list.add(toDto(computer));
        }
        return list;
    }
    public String getResourceType(){return "Computer" ; }
}