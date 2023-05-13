package org.resources.restmanager.model.DTO.lalle;

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

public class ordinateurDto implements Serializable {
    private long id;
    private String barCode;
    private Date dateOfRequest;
    private Date deliveryDate;
    private Date warrantyDate;
    private int state;
    //private boolean estPartager;
    private String brand;
    private String cpu;
    private String disk;
    private String screen;
    private int ram;
    private String providerName;

    //private EnseignantDto enseignantDto;

    public static ordinateurDto toDto(Computer computer){
        return ordinateurDto.builder().
            id(computer.getId())
        .barCode(computer.getBarCode())
                .dateOfRequest(computer.getDateOfRequest())
                .deliveryDate(computer.getDeliveryDate())
                .warrantyDate(computer.getWarrantyDate())
                .state(computer.getState())
                .brand(computer.getBrand())
                .screen(computer.getScreen())
                .cpu(computer.getCPU())
                .disk(computer.getDisk())
                .ram(computer.getRAM())
                .providerName(computer.getProviderName())
                .build();
    }

  public static List<ordinateurDto> toDtoList(List<Computer> computerList) {
    List<ordinateurDto> list = new ArrayList<>();
    for (Computer computer : computerList) {
      list.add(toDto(computer));
    }
    return list;
  }
  public String getResourceType(){return "computer" ; }

    public static boolean equals(ordinateurDto ordinateurDto, Computer computer){
        return (ordinateurDto.cpu.equals(computer.getCPU()) &&
                ordinateurDto.disk == computer.getDisk() &&
                ordinateurDto.screen == computer.getScreen() &&
                ordinateurDto.ram == computer.getRAM()) ;
    }

}
