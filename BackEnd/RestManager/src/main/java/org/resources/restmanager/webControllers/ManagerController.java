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
    public ResponseEntity<List<OrdinateurDto>> getComputers(@PathVariable int state){

        return new ResponseEntity<>(resourcesService.getComputersByState(state) , HttpStatus.OK) ;
    }

    @GetMapping("/liste-ordinateurs")
    public ResponseEntity<List<OrdinateurDto>> getAllComputers(){

        return new ResponseEntity<>(resourcesService.getAllComputers() , HttpStatus.OK) ;
    }

    @PutMapping("/ordinateur/{id}")
    public ResponseEntity<OrdinateurDto> putComputer(@PathVariable("id") final long id, @RequestBody OrdinateurDto ordinateurDto){

        return new ResponseEntity<>(resourcesService.updateComputerById(id, ordinateurDto) , HttpStatus.CREATED) ;
    }


    @GetMapping("/liste-imprimantes/{state}")
    public ResponseEntity<List<ImprimanteDto>> getPrinters(@PathVariable int state){
        return new ResponseEntity<>(resourcesService.getPrintersByState(state) , HttpStatus.OK) ;
    }

    @GetMapping("/liste-imprimantes")
    public ResponseEntity<List<ImprimanteDto>> getAllPrinters(){
        return new ResponseEntity<>(resourcesService.getAllPrinters() , HttpStatus.OK) ;
    }

    @PutMapping("/imprimente/{id}")
    public ResponseEntity<ImprimanteDto> putComputer(@PathVariable("id") final long id, @RequestBody ImprimanteDto imprimanteDto){

        return new ResponseEntity<>(resourcesService.updatePrinterById(id, imprimanteDto) , HttpStatus.CREATED) ;
    }


}
