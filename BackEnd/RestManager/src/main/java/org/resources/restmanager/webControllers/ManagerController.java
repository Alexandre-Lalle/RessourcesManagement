package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.mimoune.ImprimanteDto;
import org.resources.restmanager.model.DTO.mimoune.OrdinateurDto;
import org.resources.restmanager.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsable")
public class ManagerController {

    @Autowired
    private ResourcesService resourcesService;

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
    public ResponseEntity<List<ImprimanteDto>> getAllPrinters(){
        return new ResponseEntity<>(resourcesService.getAllPrinters() , HttpStatus.OK) ;
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


}
