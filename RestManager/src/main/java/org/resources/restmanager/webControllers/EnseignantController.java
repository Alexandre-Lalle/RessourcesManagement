package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.mimoune.*;
import org.resources.restmanager.model.DTO.mouhsine.UserDTO;
import org.resources.restmanager.model.DTO.zouine.ComputerDemandDTO;
import org.resources.restmanager.model.DTO.zouine.DemandDTO;
import org.resources.restmanager.model.DTO.zouine.PrinterDemandDTO;
import org.resources.restmanager.model.entities.Failure;
import org.resources.restmanager.model.entities.Teacher;
import org.resources.restmanager.model.entities.auth.User;
import org.resources.restmanager.services.DepartmentService;
import org.resources.restmanager.services.ResourcesService;
import org.resources.restmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/Recources-Managment/enseignant")
public class EnseignantController {
    final
    ResourcesService resourcesService;
    private DepartmentService teacherService;
    @Autowired
    UserService userService;


    public EnseignantController(ResourcesService resourcesService, DepartmentService teacherService) {
        this.resourcesService = resourcesService;
        this.teacherService = teacherService;
    }


    @GetMapping(path = "/liste-demandes/{department}")
    public List<DemandeDto> getRequests(@PathVariable String department){
        return  resourcesService.getRequests(department);
    }


//    @GetMapping("/liste-demandes/besoin/{demande_id}/{ens_id}")
//    public BesoinDto getBesoin(@PathVariable long demande_id,@PathVariable("ens_id") Long ensID){
//        return  resourcesService.getBesoin(ensID,demande_id);
//    }


    @GetMapping(value = "/liste-ordinateurs/{id}", produces = {"application/json"})
    public List<OrdinateurDto> getComputers(@PathVariable Long id){
     return resourcesService.getCumputers(id);
    }
//
//
    @GetMapping(path = "/liste-imprimantes/{id}", produces = {"application/json"})
    public List<ImprimanteDto> getPrinters(@PathVariable Long id){
        return resourcesService.getPrinters(id);
    }
//    @PostMapping(path ="/ajouter-demande", produces = {"application/json"})
//    public BesoinDto saveRequest(@RequestBody BesoinDto besoinDto){
//        System.err.println(besoinDto);
//        return resourcesService.saveRequest(besoinDto);
//    }

//    @PostMapping(path ="/modifier-ordinateur", produces = {"application/json"})
//    public OrdinateurDto editRequestCumputer(@RequestBody OrdinateurDto ordinateurDto){
//        return resourcesService.editRequestComputer(ordinateurDto);
//    }
//    @PostMapping(path ="/modifier-imprimante", produces = {"application/json"})
//    public ImprimanteDto editRequestCumputer(@RequestBody ImprimanteDto imprimanteDto){
//        return resourcesService.editRequestPrinter(imprimanteDto);
//    }
    @PostMapping(path ="/signaler-panne/{enseignant_id}", produces = {"application/json"})
    public Failure repFailure(@RequestBody PanneDto panneDto,@PathVariable Long enseignant_id){
        return resourcesService.repFailure(panneDto,enseignant_id);
    }

    @GetMapping(value = "/test")
    public String getSoumissions(){
        System.out.println("hejkhdjkgh=================================================");
        return "ok";
    }



    //////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(path = "/demands/{id}",produces = {"application/json"})
    public Optional<DemandDTO> getDemand(@PathVariable Long id){
        return resourcesService.getDemand(id);
    }


    @GetMapping(path = "/demands/teacher/{teacherId}",produces = {"application/json"})
    public List<DemandDTO> getAllDemands(@PathVariable Long teacherId){
        return resourcesService.getAllDemands(teacherId);
    }


    @DeleteMapping(path = "/demands/{id}",produces = {"application/json"})
    public boolean deleteDemand(@PathVariable Long id){
        return resourcesService.deleteDemand(id);
    }


    @PostMapping(path = "/send-demands",consumes = {"application/json"})
    public boolean sendUnavailableDemands(@RequestBody String department){
        return resourcesService.sendUnavailableDemands(department);
    }

    @PostMapping(path = "/computer/demands",produces = {"application/json"},consumes = {"application/json"})
    public boolean addComputerDemand(@RequestBody ComputerDemandDTO demand){
        System.out.println("add function was called ! :\n"+demand);
        return resourcesService.addComputerDemand(demand);
    }

    @PostMapping(path = "/printer/demands",produces = {"application/json"},consumes = {"application/json"})
    public boolean addPrinterDemand(@RequestBody PrinterDemandDTO printerDemand){
        System.out.println("add function was called !");
        return resourcesService.addPrinterDemand(printerDemand);
    }


    @GetMapping(path = "/teachers/{department}",produces = {"application/json"})
    public List<Teacher> getTeachers(@PathVariable String department){
        return teacherService.getTeachers(department);
    }

    @GetMapping(path = "/teachers/mails/{department}",produces = {"application/json"})
    public List<String> getTeacherMails(@PathVariable String department){
        return teacherService.getTeacherMails(department);
    }

    @GetMapping(path = "/departments",produces = {"application/json"})
    public List<String> getAllDepartments(){
        return teacherService.getAllDepartements();
    }

    ////////////////////////// user //////////////////////////////


    @GetMapping("/allUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @PostMapping("/createUser")
    public UserDTO createOffre(@RequestBody UserDTO userDTO) {
        User u = UserDTO.toEntity(userDTO);
        u.setPassword("1234");
        return UserDTO.toDto(userService.addUser(u));
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser((Long)id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
