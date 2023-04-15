package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.mimoune.*;
import org.resources.restmanager.model.entities.Failure;
import org.resources.restmanager.services.DepartmentService;
import org.resources.restmanager.services.ResourcesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enseignant")
public class EnseignantController {
    final
    ResourcesService resourcesService;


    public EnseignantController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }


    @GetMapping("/liste-demandes/{departement_id}")
    public List<DemandeDto> getRequests(@PathVariable String departement){
        return  resourcesService.getRequests(departement);
    }


    @GetMapping("/liste-demandes/besoin/{demande_id}/{ens_id}")
    public BesoinDto getBesoin(@PathVariable long demande_id,@PathVariable("ens_id") Long ensID){
        return  resourcesService.getBesoin(ensID,demande_id);
    }


    @GetMapping("/liste-ordinateurs/{id}")
    public List<OrdinateurDto> getComputers(@PathVariable Long id){
     return resourcesService.getCumputers(id);
    }


    @GetMapping("/liste-imprimantes/{id}")
    public List<ImprimanteDto> getPrinters(@PathVariable Long id){
        return resourcesService.getPrinters(id);
    }
    @PostMapping("/ajouter-demande")
    public BesoinDto saveRequest(@RequestBody BesoinDto besoinDto){
        return resourcesService.saveRequest(besoinDto);
    }
    @PostMapping("/modifier-ordinateur")
    public OrdinateurDto editRequestCumputer(@RequestBody OrdinateurDto ordinateurDto){
        return resourcesService.editRequestComputer(ordinateurDto);
    }
    @PostMapping("/modifier-imprimante")
    public ImprimanteDto editRequestCumputer(@RequestBody ImprimanteDto imprimanteDto){
        return resourcesService.editRequestPrinter(imprimanteDto);
    }
    @PostMapping("/signaler-panne")
    public Failure repFailure(@RequestBody PanneDto panneDto){
        return resourcesService.repFailure(panneDto);
    }


}
