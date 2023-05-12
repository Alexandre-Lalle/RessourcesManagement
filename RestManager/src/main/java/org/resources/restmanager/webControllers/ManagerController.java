package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.lalle.AffectationDto;
import org.resources.restmanager.model.DTO.lalle.imprimanteDto;
import org.resources.restmanager.model.DTO.lalle.ordinateurDto;
import org.resources.restmanager.model.DTO.mouhsine.OffreDTO;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.services.AffectationService;
import org.resources.restmanager.services.OffreService;
import org.resources.restmanager.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AffectationService affectationService;


    @GetMapping("/liste-ordinateurs/{state}")
    public List<ordinateurDto> getComputersByState(@PathVariable int state){

        return resourcesService.getComputersByState(state);
    }

    @GetMapping("/liste-ordinateurs")
    public List<ordinateurDto> getAllComputers(){

        return resourcesService.getAllComputers();
    }

    @GetMapping("/ordinateur/{id}")
    public ordinateurDto getComputerById(@PathVariable("id") final long id){

        return resourcesService.getComputerById(id);
    }

    @PutMapping("/ordinateur/{id}")
    public ordinateurDto updateComputer(@PathVariable("id") final long id, @RequestBody ordinateurDto ordinateurDto){

        return resourcesService.updateComputerById(id, ordinateurDto);
    }

    @DeleteMapping("/ordinateur/{id}")
    public void deleteComputer(@PathVariable("id") final long id){
        resourcesService.deleteComputerById(id);
    }

    @GetMapping("/liste-imprimantes/{state}")
    public List<imprimanteDto> getPrintersByState(@PathVariable int state){
        return resourcesService.getPrintersByState(state);
    }

    @GetMapping("/liste-imprimantes")
    public List<imprimanteDto> getAllPrinters(){
        return resourcesService.getAllPrinters();
    }

    @PutMapping("/imprimante/{id}")
    public imprimanteDto updatePrinter(@PathVariable("id") final long id, @RequestBody imprimanteDto imprimanteDto){

        return resourcesService.updatePrinterById(id, imprimanteDto);
    }

    @GetMapping("/imprimante/{id}")
    public imprimanteDto getPrinterById(@PathVariable("id") final long id){

        return resourcesService.getPrinterById(id);
    }

    @DeleteMapping("/imprimante/{id}")
    public void deletePrinter(@PathVariable("id") final long id){
        resourcesService.deletePrinterById(id);
    }

    @PostMapping(path = "/add-affectation",produces = {"application/json"},consumes = {"application/json"})
    public AffectationDto addAffectation(@RequestBody AffectationDto affectationDto){
        System.out.println("add function was called !");
        return affectationService.createAffectation(affectationDto);
    }
    @GetMapping("/affectation/{id}")
    public AffectationDto getAffectationByResourceId(@PathVariable("id") final long id){

        return affectationService.getAffectationByResourceId(id);
    }

    @PutMapping("/update-affectation")
    public AffectationDto updateAffectation(@RequestBody AffectationDto affectationDto){

        return affectationService.updateAffectation(affectationDto);
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
