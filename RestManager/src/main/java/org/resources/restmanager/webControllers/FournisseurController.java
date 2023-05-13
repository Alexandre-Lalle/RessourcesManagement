package org.resources.restmanager.webControllers;



import org.resources.restmanager.model.DTO.mimoune.FournisseurDto;
import org.resources.restmanager.model.DTO.mimoune.DemandeRetourDto;
import org.resources.restmanager.model.DTO.mimoune.OffreDto;
import org.resources.restmanager.model.DTO.mimoune.SoumissionDto;
import org.resources.restmanager.services.FournisseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Recources-Managment/fournisseur")
public class FournisseurController {
    final
    FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }
    @GetMapping(value = "/liste-offres" ,  produces = {"application/json"})
    public List<OffreDto> getOffres(){
        return fournisseurService.getOffres();
    }


    // les soumissions
    @GetMapping(value = "/liste-soumissions/{fournisseur_id}" ,  produces = {"application/json"})
    public List<SoumissionDto> getSoumissions( @PathVariable Long fournisseur_id){
        return fournisseurService.getSoumissions(fournisseur_id);
    }
    @PostMapping(value = "/ajouter-soumission/{fournisseur_id}",  produces = {"application/json"})
    public SoumissionDto addSoumission(@RequestBody SoumissionDto soumissionDto,@PathVariable Long fournisseur_id){
        soumissionDto.setFournisseurDto(new FournisseurDto(fournisseur_id));
        return fournisseurService.addSoumission(soumissionDto);
    }
    @GetMapping(value = "/supprimer-soumission/{soumission_id}",  produces = {"application/json"})
    public void supprimerSoumission( @PathVariable Long soumission_id){
        fournisseurService.deleteSoumission(soumission_id);
    }
    @PostMapping(value = "/modifier-soumission",  produces = {"application/json"})
    public SoumissionDto modifySoumission(@RequestBody SoumissionDto soumissionDto){
        System.out.println(soumissionDto);
        return fournisseurService.modifySoumission(soumissionDto);
    }
    /// les message de retour
    @GetMapping(value = "/liste-messages/{fournisseur_id}",  produces = {"application/json"})
    public List<DemandeRetourDto> getMessages(@PathVariable Long fournisseur_id){
        List<DemandeRetourDto> demandeRetourDtoList =  fournisseurService.getMessages(fournisseur_id) ;
        return demandeRetourDtoList;
    }



}
