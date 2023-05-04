package org.resources.restmanager.webControllers;



import org.resources.restmanager.model.DTO.mimoune.FournisseurDto;
import org.resources.restmanager.model.DTO.mimoune.OffreDto;
import org.resources.restmanager.model.DTO.mimoune.SoumissionDto;
import org.resources.restmanager.model.entities.Soumission;
import org.resources.restmanager.services.FournisseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fournisseur")
public class FournisseurController {
    final
    FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }
    @GetMapping("/liste-offres")
    public List<OffreDto> getOffres(){
       return fournisseurService.getOffres();
    }

    @PostMapping("/ajouter-soumission")
    public Soumission addSoumission(@RequestBody SoumissionDto soumissionDto){
        System.err.println(soumissionDto);
        soumissionDto.setFournisseurDto(new FournisseurDto(1L));
        return fournisseurService.addSoumission(soumissionDto);
    }
    @GetMapping("/liste-soumissions")
    public List<SoumissionDto> getSoumissions(){
        long founissour_id= 1;
    return fournisseurService.getSoumissions(founissour_id);
    }
}
