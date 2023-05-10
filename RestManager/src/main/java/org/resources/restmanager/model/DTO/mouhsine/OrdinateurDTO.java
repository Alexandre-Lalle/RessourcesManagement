package org.resources.restmanager.model.DTO.mouhsine;


import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.experimental.SuperBuilder;
import org.resources.restmanager.model.entities.Computer;

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
    private String disk;
    private String screen;
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
                .screen(computer.getScreen())
                .cpu(computer.getCPU())
                .disk(computer.getDisk())
                .ram(computer.getRAM())
                .build();
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

    public static List<OrdinateurDTO> toDtoList(List<Computer> computerList) {
        List<OrdinateurDTO> list = new ArrayList<>();
        for (Computer computer : computerList) {
            list.add(toDto(computer));
        }
        return list;
    }
    public String getResourceType(){return "Computer" ; }
}