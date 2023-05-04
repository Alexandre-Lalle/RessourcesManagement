package org.resources.restmanager.model.DTO.mimoune;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class BesoinDto implements Serializable {
    private OrdinateurDto ordinateurDto;
    private ImprimanteDto imprimanteDto;

}
