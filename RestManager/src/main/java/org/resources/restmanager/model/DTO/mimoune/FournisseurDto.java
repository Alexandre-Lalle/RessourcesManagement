package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.Provider;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FournisseurDto implements Serializable {
    private Long id;
    private String mail;
    private String gerant;
    private String nom_socite;

    private List<OrdinateurDto> ordinateurDtoList;
    private List<ImprimanteDto> imprimanteDtoList;

    public static FournisseurDto toDto( Provider provider) {
        return FournisseurDto.builder().
                id(provider.getId())
                .mail(provider.getEmail())
                .gerant(provider.getManager())
                .nom_socite(provider.getName())
                .build();
    }

    public FournisseurDto(Long id) {
        this.id = id;
    }
}
