package org.resources.restmanager.services;

import org.resources.restmanager.model.DTO.zouine.ComputerDemandDTO;
import org.resources.restmanager.model.DTO.zouine.DemandDTO;
import org.resources.restmanager.model.DTO.zouine.FailureDTO;
import org.resources.restmanager.model.DTO.zouine.PrinterDemandDTO;
import org.resources.restmanager.model.DTO.mimoune.*;
import org.resources.restmanager.model.DTO.lalle.*;
import org.resources.restmanager.model.entities.*;
import org.resources.restmanager.model.entities.auth.User;
import org.resources.restmanager.repositories.*;
import org.resources.restmanager.repositories.auth.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourcesService {
    private AppUserRepository userRepository;
    private ResourceRepository resourceRepository;
    private OrdinateurRepo ordinateurRepo;
    private ImprimanteRepo imprimanteRepo;
    private FailureRepository failureRepository;
    private ReportRepository reportRepository;
    private NotificationRepository notificationRepository;
    private TeacherRepository teacherRepository;


    @Autowired
    public ResourcesService(AppUserRepository userRepository,
                            ResourceRepository resourceRepository,
                            FailureRepository failureRepository,
                            ReportRepository reportRepository,
                            NotificationRepository notificationRepository,
                            OrdinateurRepo ordinateurRepo,
                            ImprimanteRepo imprimanteRepo,
                            TeacherRepository teacherRepository){
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.failureRepository = failureRepository;
        this.reportRepository = reportRepository;
        this.notificationRepository = notificationRepository;
        this.ordinateurRepo = ordinateurRepo;
        this.imprimanteRepo = imprimanteRepo;
        this.teacherRepository = teacherRepository;
    }

    public Optional<DemandDTO> getDemand(Long id){
        List<DemandDTO> demands = new ArrayList<>();
        Optional<Resource> resource = resourceRepository.findById(id);
        List<Teacher> teachers;
        return resource.map(value -> Optional.of(DemandDTO.toDTO(value, value.getTeachers()))).orElse(null);
    }

    public List<DemandDTO> getAllDemands(){
        List<DemandDTO> demands = new ArrayList<>();
        List<Resource> resources = resourceRepository.findAll();
        List<Teacher> teachers;
        for (Resource resource:resources) {
            demands.add(DemandDTO.toDTO(resource,resource.getTeachers()));
        }
        return demands;
    }

    public List<DemandDTO> getAllDemands(Long teacherId){
        List<DemandDTO> demands = new ArrayList<>();
        List<Resource> resources = resourceRepository.findAllByTeachersContains(teacherId);

        for (Resource resource:resources) {
            demands.add(DemandDTO.toDTO(resource,resource.getTeachers()));
        }
//
//        User u =  userRepository.getReferenceById(teacherId);
//        System.out.println("====================== teacher : "+u);
//        if(u instanceof Teacher) {
//            List<Teacher> teachers;
//            for (Resource resource:resources) {
//                demands.add(DemandDTO.toDTO(resource,resource.getTeachers()));
//            }
//        }
        return demands;
    }

    public List<DemandDTO> getAllDemands(String department){
        List<DemandDTO> demands = new ArrayList<>();
        List<Resource> resources = resourceRepository.findAllDemandsByDepartment(department);
        List<Teacher> teachers;
        for (Resource resource:resources) {
            demands.add(DemandDTO.toDTO(resource,resource.getTeachers()));
        }
        return demands;
    }


    public boolean deleteDemand(Long id){
        try{
            resourceRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("Error while deleting the demand : "+e.getMessage());
            return false;
        }
    }

    public boolean sendUnavailableDemands(String department){
        try{
            List<Resource> resources = resourceRepository.findAllDemandsByDepartment(department);
            for(Resource rc :resources){
                rc.setState(0);
            }
            resourceRepository.saveAll(resources);
            System.out.println("was set  =========================== : "+resources);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addComputerDemand(ComputerDemandDTO computerDemand){
        Resource resource = computerDemand.getComputer();
        resource.setTeachers(computerDemand.getTeachers());
        try{
            resourceRepository.save(resource);
            return true;
        }catch (Exception e){
            System.err.println("Error while saving the resource : \n"+e.getMessage());
            return false;
        }
    }

    public boolean addPrinterDemand(PrinterDemandDTO printerDemand){
        Resource resource = printerDemand.getPrinter();
        resource.setTeachers(printerDemand.getTeachers());
        try{
            resourceRepository.save(resource);
            return true;
        }catch (Exception e){
            System.err.println("Error while saving the resource : \n"+e.getMessage());
            return false;
        }
    }

    public List<FailureDTO> getAllFailures(){
        List<Failure> failures = failureRepository.findAll();
        List<FailureDTO> failuresDTO = new ArrayList<>();

        for(Failure failure:failures){
            failuresDTO.add(FailureDTO.toDTO(failure));
        }

        return failuresDTO;
    }


    public boolean updateFailure(Failure failure){
        try{
                Resource r  = failureRepository.getResourceByFailureId(failure.getId());
                Teacher t = failureRepository.getTeacherByFailureId(failure.getId());
                Report rp = failureRepository.getReportByFailureId(failure.getId());

                Failure f = failureRepository.getReferenceById(failure.getId());
                f.setResource(r);
                f.setTeacher(t);
                f.setReport(rp);
                f.setProcessed(failure.isProcessed());

                failureRepository.save(f);
//            failureRepository.updateProcessed(failure.getId(), failure.isProcessed());
//            Failure f = failureRepository.getReferenceById(failure.getId());
//            Resource r = null;
//            if(f.getResource()!=null)  r = f.getResource();
//            Teacher t=null ;
//            if(f.getTeacher()!=null) t = f.getTeacher();
//            Report rp=null ;
//            if(f.getReport()!=null) rp = f.getReport();
//            if(r!=null) f.setResource(r);
//            if(t!=null) f.setTeacher(t);
//            if(rp!=null) f.setReport(rp);
//            f.setProcessed(failure.isProcessed());
//            failureRepository.save(f);
            return true;
        }catch (Exception e){
            System.out.println("Error while updating the failure : /n"+e.getMessage());
            return false;
        }
    }

    public boolean updateReport(Report report){
        try{
            Failure f = failureRepository.getReferenceById(report.getFailure().getId());
            f.setProcessed(report.getFailure().isProcessed());
            report.setFailure(f);
            reportRepository.save(report);
            return true;
        }catch (Exception e){
            System.out.println("Error while updating the report : \n"+e.getMessage());
            return false;
        }
    }

    public boolean deleteReport(Long id){
        try{
            reportRepository.deleteById(id);
            return true;
        }catch(Exception e){
            System.out.println("Error while deleting the report : \n"+e.getMessage());
            return false;
        }
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////


    public List<DemandeDto> getRequests(String departement){
        List<DemandeDto> demandeDtoList= new ArrayList<>();
        notificationRepository.findDemandesByDepartementd_IdOrderByIdDesc(departement).forEach(
                notification -> {
                    demandeDtoList.add(DemandeDto.toDto(notification));
                }
        );
        return demandeDtoList ;
    }
    public List<OrdinateurDto> getCumputers(Long enseignant_id){
        List<OrdinateurDto> ordinateurDtoList= new ArrayList<>();
        System.out.println(enseignant_id);

        ordinateurRepo.findByEnseignant_Id(enseignant_id).forEach(
                ordinateur -> {
//                    List<Failure> failures = resourceRepository.getFailuresByResourceId(ordinateur.getId());
//                    System.out.println("failures : "+failures);
//                    ordinateur.setFailures(failures);

                    ordinateurDtoList.add(OrdinateurDto.toDto(ordinateur));
                }
        );
        return ordinateurDtoList;
    }

    public List<ImprimanteDto> getPrinters(Long enseignant_id) {
        List<ImprimanteDto> imprimanteDtoList= new ArrayList<>();
        imprimanteRepo.findByEnseignant_Id(enseignant_id).forEach(
                printer -> {
//                    List<Failure> failures = resourceRepository.getFailuresByResourceId(printer.getId());
//                    System.out.println("failures : "+failures);
//                    printer.setFailures(failures);
                    imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                }
        );
        return imprimanteDtoList;
    }

    public BesoinDto saveRequest(BesoinDto besoinDto) {
        if(besoinDto.getImprimanteDto() != null){
            ImprimanteDto imprimanteDto  =besoinDto.getImprimanteDto();
            if(imprimanteDto.getResolution()!=null && imprimanteDto.getPrintSpeed() != 0) {
                Long id = besoinDto.getEnseignant_id();
                List<Teacher> teachers = new ArrayList<>();
                teachers.add(new Teacher(id));
                imprimanteDto = ImprimanteDto.toDto(
                        resourceRepository.save(
                                Printer.builder()
                                        .state(-1)
                                        .provider(null)
                                        .teachers(teachers)
                                        .printSpeed(imprimanteDto.getPrintSpeed())
                                        .resolution(imprimanteDto.getResolution())
                                        .build()
                        )
                );
                besoinDto.setImprimanteDto(imprimanteDto);
            }
        }
        if(besoinDto.getOrdinateurDto() != null) {
            OrdinateurDto ordinateurDto = besoinDto.getOrdinateurDto();
            if (ordinateurDto.getRAM() != 0 && ordinateurDto.getDisk() != null && !ordinateurDto.getCPU().equals("")) {
                Long id = besoinDto.getEnseignant_id();
                List<Teacher> teachers = new ArrayList<>();
                teachers.add(new Teacher(id));
                ordinateurDto = OrdinateurDto.toDto(
                        resourceRepository.save(Computer.builder()
                                .state(-1)
                                .teachers(teachers)
                                .CPU(ordinateurDto.getCPU())
                                .disk(ordinateurDto.getDisk())
                                .screen(ordinateurDto.getScreen())
                                .RAM(ordinateurDto.getRAM())
                                .build())
                );
                besoinDto.setOrdinateurDto(ordinateurDto);
            }
        }
        return besoinDto;
    }

    public OrdinateurDto editRequestComputer(OrdinateurDto ordinateurDto) {
        if( ordinateurDto.getState() != 1 ) {
            Long id = ordinateurDto.getEnseignantDto().getId();
            List<Teacher> teachers = new ArrayList<>();
            teachers.add(new Teacher(id));
            ordinateurDto = OrdinateurDto.toDto(
                    resourceRepository.save(Computer.builder()
                            .id(ordinateurDto.getId())
                            .state(-1)
                            .teachers(teachers)
                            .CPU(ordinateurDto.getCPU())
                            .disk(ordinateurDto.getDisk())
                            .screen(ordinateurDto.getScreen())
                            .RAM(ordinateurDto.getRAM())
                            .build())
            )  ;
            return ordinateurDto;
        }
        else return null;
    }
    public ImprimanteDto editRequestPrinter(ImprimanteDto imprimanteDto) {
        if(imprimanteDto.getState() == 0){
            Long id = imprimanteDto.getEnseignantDto().getId();
            List<Teacher> teachers = new ArrayList<>();
            teachers.add(new Teacher(id));
            imprimanteDto = ImprimanteDto.toDto(
                    resourceRepository.save(
                            Printer.builder()
                                    .barCode(imprimanteDto.getBarCode())
                                    .state(-1)
                                    .teachers(teachers)
                                    .printSpeed(imprimanteDto.getPrintSpeed())
                                    .resolution(imprimanteDto.getResolution())
                                    .build()
                    )
            ) ;
            return imprimanteDto;
        }else return null;
    }

    public Failure repFailure(PanneDto panneDto,Long enseignant_id) {
        long id  =panneDto.getRessourceDto().getId();
        Resource resource = new Resource(){};
        resource.setId(id);
        Failure failure = Failure.builder()
                .processed(false)
                .resource(resource)
                .teacher(new Teacher(enseignant_id))
                .date(panneDto.getDate()).build();
        return failureRepository.save(failure);
    }

    public BesoinDto getBesoin(long enseignantId, long demandeId) {
        List<Resource> ressourceList = resourceRepository.findByEnseignant_IdAndDemande_Id(enseignantId,demandeId);
        BesoinDto besoinDto = new BesoinDto();
        ressourceList.forEach( ressource -> {
            String className = ressource.getClass().getSimpleName();
            if (className.equals("Imprimante"))besoinDto.setImprimanteDto(ImprimanteDto.toDto((Printer) ressource));
            else besoinDto.setOrdinateurDto(OrdinateurDto.toDto((Computer) ressource));
        });
        return besoinDto;
    }


    ////////////////////////////// Méthodes de gestion des ressources par le Responsable  /////////////////////////////////////////////////////////////////


    public List<ordinateurDto> getAllComputers(){
        List<ordinateurDto> ordinateurDtoList= new ArrayList<>();
        ordinateurRepo.findAll().forEach(
                computer -> { ordinateurDtoList.add(ordinateurDto.toDto(computer));}
        );
        return ordinateurDtoList;
    }

    public List<ordinateurDto> getComputersByState(int state){
        List<ordinateurDto> ordinateurDtoList= new ArrayList<>();
        ordinateurRepo.findByState(state).forEach(
                computer -> { ordinateurDtoList.add(ordinateurDto.toDto(computer)); });
        return ordinateurDtoList;
    }

    public ordinateurDto getComputerById(final Long id) {
        Optional<Computer> ordinateur = ordinateurRepo.findById(id);
        ordinateurDto ordinateurdto = ordinateurDto.toDto(ordinateur.get());

        return ordinateurdto;
    }

    public boolean deleteComputerById(final Long id) {
        try{
            ordinateurRepo.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("Error when deleting the computer : \n"+e.getMessage());
            return false;
        }
    }

    public ordinateurDto updateComputerById(Long id, ordinateurDto ordinateurDetails) {
        Optional<Computer> currentOrdinateur = ordinateurRepo.findById(id);

        if(currentOrdinateur.isPresent()) {
            Computer ordinateur = currentOrdinateur.get();
            ordinateur.setBrand(ordinateurDetails.getBrand());
            ordinateur.setState(ordinateurDetails.getState());
            ordinateur.setBarCode(ordinateurDetails.getBarCode());
            ordinateur.setCPU(ordinateurDetails.getCpu());
            ordinateur.setScreen(ordinateurDetails.getScreen());
            ordinateur.setRAM(ordinateurDetails.getRam());
            ordinateur.setDeliveryDate(ordinateurDetails.getDeliveryDate());
            ordinateur.setWarrantyDate(ordinateurDetails.getWarrantyDate());

            ordinateurDto updated = ordinateurDto.toDto(ordinateurRepo.save(ordinateur));
            return updated;
        }
        else {
            // Throw new ResourceNotFoundException("Ordinateur", "id", id)) ?
        }
        return null;
    }

    public List<imprimanteDto> getPrintersByState(int state) {
        List<imprimanteDto> imprimanteDtoList= new ArrayList<>();
        imprimanteRepo.findByState(state).forEach(
                printer -> {
                    imprimanteDtoList.add(imprimanteDto.toDto(printer));
                }
        );
        return imprimanteDtoList;
    }

    public imprimanteDto getPrinterById(final Long id) {
        Optional<Printer> imprimante = imprimanteRepo.findById(id);
        imprimanteDto imprimantedto = imprimanteDto.toDto(imprimante.get());

        return imprimantedto;
    }

    public boolean deletePrinterById(final Long id) {
        try{
            imprimanteRepo.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("Error when deleting the printer : \n"+e.getMessage());
            return false;
        }
    }

    public List<imprimanteDto> getAllPrinters() {
        List<imprimanteDto> imprimanteDtoList= new ArrayList<>();
        imprimanteRepo.findAll().forEach(
                printer -> {
                    imprimanteDtoList.add(imprimanteDto.toDto(printer));
                }
        );
        return imprimanteDtoList;
    }

    public imprimanteDto updatePrinterById(Long id, imprimanteDto PrinterDto) {
        Optional<Printer> currentPrinter = imprimanteRepo.findById(id);

        if(currentPrinter.isPresent()) {
            Printer printer = currentPrinter.get();
            printer.setBrand(PrinterDto.getBrand());
            printer.setState(PrinterDto.getState());
            printer.setBarCode(PrinterDto.getBarCode());
            printer.setPrintSpeed(PrinterDto.getPrintSpeed());
            printer.setResolution(PrinterDto.getResolution());
            printer.setDeliveryDate(PrinterDto.getDeliveryDate());
            printer.setWarrantyDate(PrinterDto.getWarrantyDate());

            imprimanteDto updated = imprimanteDto.toDto(imprimanteRepo.save(printer));
            return updated;
        }
        else {
            // Throw new ResourceNotFoundException("Ordinateur", "id", id)) ?
        }
        return null;
    }

//    public List<RessourceDto> getAllRessources(){
//        List<RessourceDto> ressourceDtoList= new ArrayList<>();
//        resourceRepository.findAll().forEach(
//                ressource -> { ressourceDtoList.add(RessourceDto.toDto(ressource));}
//        );
//        return ressourceDtoList;
//    }






    ///////////////////////////////////// abdel //////////////////////////////////////////////
    public Resource getRessourceById(Long ressourceId) {
        Optional<Resource> ressourceOptional = resourceRepository.findById(ressourceId);
        return ressourceOptional.get();
    }
    public Resource deleteOffreFromRessorce(Long ressourceId){
        Resource resource = resourceRepository.getReferenceById(ressourceId);
        resource.setOffre(null);
        return resourceRepository.save(resource);
    }

    public List<Resource> findRessourcesWithoutOffre(){
        System.out.println("San offre ================ : "+resourceRepository.findRessourcesWithoutOffre());
        return resourceRepository.findRessourcesWithoutOffre();
    }
}
