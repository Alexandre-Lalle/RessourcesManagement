package org.resources.restmanager.webControllers;

import org.resources.restmanager.model.DTO.zouine.ComputerDemandDTO;
import org.resources.restmanager.model.DTO.zouine.DemandDTO;
import org.resources.restmanager.model.DTO.zouine.PrinterDemandDTO;
import org.resources.restmanager.model.entities.Notification;
import org.resources.restmanager.model.entities.Teacher;
import org.resources.restmanager.services.ResourcesService;
import org.resources.restmanager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class DirectorController {
    private ResourcesService resourcesService;
    private DepartmentService teacherService;



    @Autowired
    public DirectorController(ResourcesService resourcesService,
                              DepartmentService teacherService){
        this.resourcesService = resourcesService;
        this.teacherService = teacherService;
    }

    @RequestMapping(path = "/Recources-Managment/demands/{id}",produces = {"application/json"})
    public Optional<DemandDTO> getDemand(@PathVariable Long id){
        return resourcesService.getDemand(id);
    }


    @GetMapping(path = "/Recources-Managment/demands/department/{department}",produces = {"application/json"})
    public List<DemandDTO> getAllDemands(@PathVariable String department, @AuthenticationPrincipal Authentication authentication){
        return resourcesService.getAllDemands(department);
    }


    @DeleteMapping(path = "/Recources-Managment/demands/{id}",produces = {"application/json"})
    public boolean deleteDemand(@PathVariable Long id){
        return resourcesService.deleteDemand(id);
    }


    @PostMapping(path = "/Recources-Managment/computer/demands",produces = {"application/json"},consumes = {"application/json"})
    public boolean addComputerDemand(@RequestBody ComputerDemandDTO demand){
        System.out.println("add function was called ! :\n"+demand);
        return resourcesService.addComputerDemand(demand);
    }

    @PostMapping(path = "/Recources-Managment/printer/demands",produces = {"application/json"},consumes = {"application/json"})
    public boolean addPrinterDemand(@RequestBody PrinterDemandDTO printerDemand){
        System.out.println("add function was called !");
        return resourcesService.addPrinterDemand(printerDemand);
    }


    @GetMapping(path = "/Recources-Managment/teachers/{department}",produces = {"application/json"})
    public List<Teacher> getTeachers(@PathVariable String department){
        return teacherService.getTeachers(department);
    }

    @GetMapping(path = "/Recources-Managment/teachers/mails/{department}",produces = {"application/json"})
    public List<String> getTeacherMails(@PathVariable String department){
        return teacherService.getTeacherMails(department);
    }

    ///////////////////////////// Failures and Reports /////////////////////////////

    @GetMapping(path = "/Recources-Managment/notifications/director/{directorId}",produces = {"application/json"})
    public List<Notification> getDirectorNotifications(@PathVariable("directorId") Long id){
        return teacherService.getNotificationByDirectorID(id);
    }

    @PostMapping(path = "/Recources-Managment/notifications",consumes = {"application/json"},produces = {"application/json"})
    public boolean addNotification(@RequestBody Notification notification){
        System.out.println("==> notification body : \n"+notification);
        return teacherService.addNotification(notification);
    }


    @DeleteMapping(path = "/Recources-Managment/notifications/{id}",produces = {"application/json"})
    public boolean deleteNotification(@PathVariable Long id){
        return teacherService.deleteNotification(id);
    }


    @GetMapping(path = "/Recources-Managment/notifications/{id}",produces = {"application/json"})
    public Optional<Notification> getNotification(@PathVariable Long id){
        return teacherService.getNotification(id);
    }

    @PutMapping(path = "/Recources-Managment/notifications",consumes = {"application/json"},produces = {"application/json"})
    public boolean updateNotification(@RequestBody Notification notification){
        return teacherService.updateNotification(notification);
    }
}
