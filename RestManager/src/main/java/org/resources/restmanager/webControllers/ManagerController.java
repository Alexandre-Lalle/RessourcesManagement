package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.mimoune.ImprimanteDto;
import org.resources.restmanager.model.DTO.mimoune.OrdinateurDto;
import org.resources.restmanager.model.DTO.mouhsine.OffreDTO;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.services.OffreService;
import org.resources.restmanager.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsable")
@CrossOrigin("*")
public class ManagerController {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private ResourcesService ressourceService;
    @Autowired
    private OffreService offreService;

    @GetMapping("/liste-ordinateurs/{state}")
    public ResponseEntity<List<OrdinateurDto>> getComputersByState(@PathVariable int state){

        return new ResponseEntity<>(resourcesService.getComputersByState(state) , HttpStatus.OK) ;
    }

    @GetMapping("/liste-ordinateurs")
    public ResponseEntity<List<OrdinateurDto>> getAllComputers(){

        return new ResponseEntity<>(resourcesService.getAllComputers() , HttpStatus.OK) ;
    }

    @GetMapping("/ordinateur/{id}")
    public ResponseEntity<OrdinateurDto> getComputerById(@PathVariable("id") final long id){

        return new ResponseEntity<>(resourcesService.getComputerById(id) , HttpStatus.OK) ;
    }

    @PutMapping("/ordinateur/{id}")
    public ResponseEntity<OrdinateurDto> updateComputer(@PathVariable("id") final long id, @RequestBody OrdinateurDto ordinateurDto){

        return new ResponseEntity<>(resourcesService.updateComputerById(id, ordinateurDto) , HttpStatus.CREATED) ;
    }

    @DeleteMapping("/ordinateur/{id}")
    public ResponseEntity<Object> deleteComputer(@PathVariable("id") final long id){
        resourcesService.deleteComputerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }

    @GetMapping("/liste-imprimantes/{state}")
    public ResponseEntity<List<ImprimanteDto>> getPrintersByState(@PathVariable int state){
        return new ResponseEntity<>(resourcesService.getPrintersByState(state) , HttpStatus.OK) ;
    }

    @GetMapping("/liste-imprimantes")
    public List<ImprimanteDto> getAllPrinters(){
        System.out.println("printer ================================================");
        return resourcesService.getAllPrinters();
    }

    @PutMapping("/imprimante/{id}")
    public ResponseEntity<ImprimanteDto> updatePrinter(@PathVariable("id") final long id, @RequestBody ImprimanteDto imprimanteDto){

        return new ResponseEntity<>(resourcesService.updatePrinterById(id, imprimanteDto) , HttpStatus.CREATED) ;
    }

    @GetMapping("/imprimante/{id}")
    public ResponseEntity<ImprimanteDto> getPrinterById(@PathVariable("id") final long id){

        return new ResponseEntity<>(resourcesService.getPrinterById(id) , HttpStatus.OK) ;
    }

    @DeleteMapping("/imprimante/{id}")
    public ResponseEntity<Object> deletePrinter(@PathVariable("id") final long id){
        resourcesService.deletePrinterById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }


    //////////////////////////////////////////// abdel //////////////////////////////////////////////////

//    @GetMapping
//    public List<Resource> getAllRessources() {
//        List<Resource> resources = ressourceService.getAllRessources();
//        return resources;
//    }

//    @GetMapping("/{id}")
//    public Resource getRessourceById(@PathVariable Long id) {
//        Resource resource = ressourceService.getRessourceById(id);
//        return resource;
//    }
//    @GetMapping("/{id}/offre")
//    public ResponseEntity<Offre> getOffreByRessourceId(@PathVariable Long id) {
//        Resource resource = ressourceService.getRessourceById(id);
//        return new ResponseEntity<>(resource.getOffre(), HttpStatus.OK);
//    }

//    @PostMapping
//    public ResponseEntity<Resource> saveRessource(@Validated @RequestBody Resource resource) {
//        Resource savedResource = ressourceService.saveRessource(resource);
//        return new ResponseEntity<>(savedResource, HttpStatus.CREATED);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Resource> updateRessource(@PathVariable Long id, @Validated @RequestBody Resource resource) {
//        Resource updatedResource = ressourceService.updateRessource(id, resource);
//        return new ResponseEntity<>(updatedResource, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
//        ressourceService.deleteRessource(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping("offres/{id}/deleteOffre")
    public Resource deleteOffreFromRessource(@PathVariable Long id){
        return ressourceService.deleteOffreFromRessorce(id);
    }

    @GetMapping("ressources/WithoutOffre")
    public List<Resource> getRessourcesWithoutOffre(){
        return ressourceService.findRessourcesWithoutOffre();
    }

    @PostMapping(value = "offres/add", consumes = "application/json")
    public OffreDTO createOffre(@RequestBody OffreDTO offre) {

        return offreService.saveOffre(offre);
    }

    // Récupérer toutes les offres
    @GetMapping("offres")
    public List<OffreDTO> getAllOffres() {
        return offreService.getAllOffres();
    }

    // Récupérer une offre par son ID
    @GetMapping("offres/{id}")
    public OffreDTO getOffreById(@PathVariable Long id) {
        return offreService.getOffreById(id);
    }

    // Mettre à jour une offre
    @PutMapping("offres")
    public OffreDTO updateOffre( @Validated @RequestBody OffreDTO offre) {
        try{
            OffreDTO new_offre = offreService.updateOffre(offre);
            return new_offre;
        }catch (Exception e){
            System.out.println("erreur can not update offre id: "+offre.getId()+": "+e.getMessage());
            return offre;
        }
    }

    // Supprimer une offre
    @DeleteMapping("offres/{id}")
    public boolean deleteOffre(@PathVariable Long id) {
        try {
            offreService.deleteOffre((Long)id);
            return true;
        }catch (Exception e){
            System.out.println("erreur can not delete offre id: "+id+": "+e.getMessage());
            return false;
        }
    }

}
