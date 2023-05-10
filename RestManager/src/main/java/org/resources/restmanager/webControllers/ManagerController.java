package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.Old.ImprimanteDto;
import org.resources.restmanager.model.DTO.Old.OrdinateurDto;
import org.resources.restmanager.model.DTO.lalle.*;
import org.resources.restmanager.model.DTO.mouhsine.OffreDTO;
import org.resources.restmanager.model.DTO.rachid.DemandeRetourDto;
import org.resources.restmanager.model.DTO.rachid.ReportDto;
import org.resources.restmanager.model.entities.DemandeRetour;
import org.resources.restmanager.model.entities.Provider;
import org.resources.restmanager.model.entities.Report;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.services.*;
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
    @Autowired
    private ConsulterFournisseurService consulterFournisseurService;
    @Autowired
    private DemandeRetourservice demandeRetourservice;
    @Autowired
    private ReportService reportService;
    @Autowired
    private AffectationService affectationService;


    @GetMapping("/consulterFournisseur/all")
    public ResponseEntity<List<Provider>> getAllFournisseurs(){
        List<Provider> fournisseurs = consulterFournisseurService.findAllProviders2();
        for (Provider fournisseur1 : fournisseurs) {
            System.out.println(fournisseur1.toString());
        }
        return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
    }
    @GetMapping("/consulterFournisseur/all2")
    public ResponseEntity<List<Provider>> getAllFournisseurs2(){
        List<Provider> fournisseurs = consulterFournisseurService.findAllProviders2();
        for (Provider fournisseur1 : fournisseurs) {
            System.out.println(fournisseur1.toString());
        }
        return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
    }
    @PostMapping("/consulterFournisseur/find")
    public ResponseEntity<Provider> findFournisseur(@RequestBody Provider fournisseur){
        Provider newFournisseur = consulterFournisseurService.findProvider(fournisseur);
        return new ResponseEntity<>(newFournisseur, HttpStatus.OK);
    }
    @PostMapping("/consulterPannes/add")
    public DemandeRetourDto addDemandeRetour(@RequestBody DemandeRetourDto demandeRetour){
        System.out.println(demandeRetour);
        demandeRetourservice.addDemandeRetour(demandeRetour);
        return null ;
    }
    @PutMapping("/consulterFournisseur/update")
    public ResponseEntity<Provider> updateEmployee(@RequestBody Provider fournisseur){
        Provider updateFournisseur = consulterFournisseurService.updateProvider(fournisseur);
        return new ResponseEntity<>(updateFournisseur,HttpStatus.OK);
    }

    @DeleteMapping("/consulterFournisseur/delete/{id}")
    public ResponseEntity<?> deleteFournisseur(@PathVariable("id") Long id) {
        consulterFournisseurService.deleteProviderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PostMapping("/consulterPannes/add")
//    public ResponseEntity<DemandeRetourDto> addDemandeRetour(@RequestBody DemandeRetourDto demandeRetour){
////        Resource r = resourcesService.getRessourceById(demandeRetour.getResource().getId());
//        DemandeRetourDto newDemandeRetour = demandeRetourservice.addDemandeRetour(demandeRetour);
//        return new ResponseEntity<>(newDemandeRetour, HttpStatus.CREATED);
//    }
    @GetMapping("/consulterPannes/all")
    public ResponseEntity<List<Report>> getAllReports(){
        List<Report> reports = reportService.findAllReports();
        return  new ResponseEntity<>(reports, HttpStatus.OK);
    }
    @GetMapping("/consulterPannes/reportInfo")
    public ResponseEntity<List<ReportDto>> getAllReportInfo(){
        List<ReportDto> reports = reportService.getReportInfo();
        return  new ResponseEntity<>(reports, HttpStatus.OK);
    }
    @PostMapping("/consulterPannes/demands")
    public ResponseEntity<List<DemandeRetour>> getALlDemandeRetour(){
        List<DemandeRetour> demandeRetours = demandeRetourservice.findAllDemandes();
        return new ResponseEntity<>(demandeRetours, HttpStatus.OK);
    }
//    @GetMapping("/consulterPannes/findDemandeByRId/{id}")
//    public ResponseEntity<DemandeRetour> findDemandeByRId(@PathVariable("id") long id){
//
//        return new ResponseEntity<>(demandeRetourservice.findDemandeByRId(id) , HttpStatus.OK) ;
//    }

    /////////////////////////// alex ///////////////////////////////////


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
    public boolean deleteComputer(@PathVariable("id") final long id){
        return resourcesService.deleteComputerById(id);
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
    public boolean deletePrinter(@PathVariable("id") final long id){
        return resourcesService.deletePrinterById(id);
    }

//    @DeleteMapping("/imprimante/{id}")
//    public Object deletePrinter(@PathVariable("id") final long id){
//        resourcesService.deletePrinterById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
//    }

    @PostMapping(path = "/add-affectation",produces = {"application/json"},consumes = {"application/json"})
    public AffectationDto addAffectation(@RequestBody AffectationDto affectationDto){
        System.out.println("add function was called !");
        System.out.println("the content : \n"+affectationDto);
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

    @GetMapping("/offres/accepterSoumission/{id}")
    public boolean accepterSoumission(@PathVariable Long id){
        try {
            offreService.accepterSoumission((Long)id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
