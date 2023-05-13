package org.resources.restmanager.webControllers;


import org.resources.restmanager.model.DTO.zouine.FailureDTO;
import org.resources.restmanager.model.entities.Failure;
import org.resources.restmanager.model.entities.Report;
import org.resources.restmanager.services.ResourcesService;
import org.resources.restmanager.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TechnicianController {
    private DepartmentService teacherService;
    private ResourcesService resourcesService;

    @Autowired
    public TechnicianController(DepartmentService teacherService, ResourcesService resourcesService){
        this.teacherService = teacherService;
        this.resourcesService = resourcesService;
    }


    @GetMapping(path = "/Resource-Managment/failures",produces = {"application/json"})
    public List<FailureDTO> getFailures(){
        return resourcesService.getAllFailures();
    }

    @PutMapping(path = "/Resource-Managment/failures", consumes = {"application/json"},produces = {"application/json"})
    public boolean updateFailures(@RequestBody Failure failure){
        return resourcesService.updateFailure(failure);
    }

    @GetMapping(path = "/Resource-Managment/reports/failures/{failureId}",produces = {"application/json"})
    public Report getReportFromFailure(@PathVariable("failureId") Long id){
        return teacherService.getReportFromFailure(id);
    }

    @PutMapping(path = "/Resource-Managment/reports",consumes = {"application/json"},produces ={"application/json"})
    public boolean updateReport(@RequestBody Report report){
        return resourcesService.updateReport(report);
    }


    @DeleteMapping(path = "/Resource-Managment/reports/{id}",produces = {"application/json"})
    public boolean deleteReport(@PathVariable Long id){
        System.out.println("was called");
        return resourcesService.deleteReport(id);
    }
}
