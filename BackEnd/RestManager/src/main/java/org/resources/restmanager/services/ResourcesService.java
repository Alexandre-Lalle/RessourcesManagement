package org.resources.restmanager.services;

import org.resources.restmanager.model.DTO.zouine.ComputerDemandDTO;
import org.resources.restmanager.model.DTO.zouine.DemandDTO;
import org.resources.restmanager.model.DTO.zouine.FailureDTO;
import org.resources.restmanager.model.DTO.zouine.PrinterDemandDTO;
import org.resources.restmanager.model.DTO.mimoune.*;
import org.resources.restmanager.model.entities.*;
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


    @Autowired
    public ResourcesService(AppUserRepository userRepository,
                            ResourceRepository resourceRepository,
                            FailureRepository failureRepository,
                            ReportRepository reportRepository,
                            NotificationRepository notificationRepository,
                            OrdinateurRepo ordinateurRepo,
                            ImprimanteRepo imprimanteRepo){
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.failureRepository = failureRepository;
        this.reportRepository = reportRepository;
        this.notificationRepository = notificationRepository;
        this.ordinateurRepo = ordinateurRepo;
        this.imprimanteRepo = imprimanteRepo;
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

    public boolean updateReport(Report report){
        try{
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
        ordinateurRepo.findByEnseignant_Id(enseignant_id).forEach(
                ordinateur -> {
                    ordinateurDtoList.add(OrdinateurDto.toDto(ordinateur));
                }
        );
        return ordinateurDtoList;
    }

    public List<ImprimanteDto> getPrinters(Long enseignant_id) {
        List<ImprimanteDto> imprimanteDtoList= new ArrayList<>();
        imprimanteRepo.findByEnseignant_Id(enseignant_id).forEach(
                printer -> {
                    imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                }
        );
        return imprimanteDtoList;
    }

    public BesoinDto saveRequest(BesoinDto besoinDto) {
        if(besoinDto.getImprimanteDto() != null){
            ImprimanteDto imprimanteDto  =besoinDto.getImprimanteDto();
            if(imprimanteDto.getResolution()!=null && imprimanteDto.getVitesse() != 0) {
                Long id = imprimanteDto.getEnseignantDto().getId();
                List<Teacher> teachers = new ArrayList<>();
                teachers.add(new Teacher(id));
                imprimanteDto = ImprimanteDto.toDto(
                        resourceRepository.save(
                                Printer.builder()
                                        .state(-1)
                                        .provider(null)
                                        .teachers(teachers)
                                        .printSpeed(imprimanteDto.getVitesse())
                                        .resolution(imprimanteDto.getResolution())
                                        .build()
                        )
                );
                besoinDto.setImprimanteDto(imprimanteDto);
            }
        }
        if(besoinDto.getOrdinateurDto() != null) {
            OrdinateurDto ordinateurDto = besoinDto.getOrdinateurDto();
            if (ordinateurDto.getRam() != 0 && ordinateurDto.getDd() != null && !ordinateurDto.getCpu().equals("")) {
                Long id = ordinateurDto.getEnseignantDto().getId();
                List<Teacher> teachers = new ArrayList<>();
                teachers.add(new Teacher(id));
                ordinateurDto = OrdinateurDto.toDto(
                        resourceRepository.save(Computer.builder()
                                .state(-1)
                                .teachers(teachers)
                                .CPU(ordinateurDto.getCpu())
                                .disk(ordinateurDto.getDd())
                                .screen(ordinateurDto.getEcran())
                                .RAM(ordinateurDto.getRam())
                                .build())
                );
                besoinDto.setOrdinateurDto(ordinateurDto);
            }
        }
        return besoinDto;
    }
    public OrdinateurDto editRequestComputer(OrdinateurDto ordinateurDto) {
        if( ordinateurDto.getEtat() != 1 ) {
            Long id = ordinateurDto.getEnseignantDto().getId();
            List<Teacher> teachers = new ArrayList<>();
            teachers.add(new Teacher(id));
            ordinateurDto = OrdinateurDto.toDto(
                    resourceRepository.save(Computer.builder()
                            .barCode(ordinateurDto.getCode())
                            .state(-1)
                            .teachers(teachers)
                            .CPU(ordinateurDto.getCpu())
                            .disk(ordinateurDto.getDd())
                            .screen(ordinateurDto.getEcran())
                            .RAM(ordinateurDto.getRam())
                            .build())
            )  ;
            return ordinateurDto;
        }
        else return null;
    }
    public ImprimanteDto editRequestPrinter(ImprimanteDto imprimanteDto) {
        if(imprimanteDto.getEtat() == 0){
            Long id = imprimanteDto.getEnseignantDto().getId();
            List<Teacher> teachers = new ArrayList<>();
            teachers.add(new Teacher(id));
            imprimanteDto = ImprimanteDto.toDto(
                    resourceRepository.save(
                            Printer.builder()
                                    .barCode(imprimanteDto.getCode())
                                    .state(-1)
                                    .teachers(teachers)
                                    .printSpeed(imprimanteDto.getVitesse())
                                    .resolution(imprimanteDto.getResolution())
                                    .build()
                    )
            ) ;
            return imprimanteDto;
        }else return null;
    }

    public Failure repFailure(PanneDto panneDto) {
        long id  =panneDto.getRessourceDto().getId();
        Resource resource = new Resource(){};
        resource.setId(id);
        Failure failure = Failure.builder()
                .processed(false)
                .resource(resource)
                .teacher(new Teacher(panneDto.getEnseignantDto().getId()))
                .date(panneDto.getDateApp()).build();
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

    public List<RessourceDto> getAllRessources(){
        List<RessourceDto> ressourceDtoList= new ArrayList<>();
        resourceRepository.findAll().forEach(
                printer -> { ressourceDtoList.add(RessourceDto.toDto(printer));}
        );
        return ressourceDtoList;
    }

    public List<OrdinateurDto> getAllComputers(){
        List<OrdinateurDto> ordinateurDtoList= new ArrayList<>();
        ordinateurRepo.findAll().forEach(
                printer -> { ordinateurDtoList.add(OrdinateurDto.toDto(printer));}
        );
        return ordinateurDtoList;
    }

    public List<OrdinateurDto> getComputersByState(int state){
        List<OrdinateurDto> ordinateurDtoList= new ArrayList<>();
        ordinateurRepo.findByState(state).forEach(
                printer -> { ordinateurDtoList.add(OrdinateurDto.toDto(printer)); });
        return ordinateurDtoList;
    }

    public OrdinateurDto updateComputerById(Long id, OrdinateurDto ordinateurDetails) {
        Optional<Computer> currentOrdinateur = ordinateurRepo.findById(id);

        if(currentOrdinateur.isPresent()) {
            Computer ordinateur = currentOrdinateur.get();
            ordinateur.setBrand(ordinateurDetails.getMarque());
            ordinateur.setState(ordinateurDetails.getEtat());
            ordinateur.setBarCode(ordinateurDetails.getCode());
            ordinateur.setCPU(ordinateurDetails.getCpu());
            ordinateur.setScreen(ordinateurDetails.getCode());
            ordinateur.setRAM(ordinateurDetails.getRam());
            ordinateur.setAssignmentDate(ordinateurDetails.getDateLiv());// DateAffectation ?
            ordinateur.setDeliveryDate(ordinateurDetails.getDateLiv());
            ordinateur.setWarrantyDate(ordinateurDetails.getDateGarantie());
            //ordinateur.setTeachers(ordinateurDetails.getEnseignantDto());
            OrdinateurDto updated = OrdinateurDto.toDto(ordinateurRepo.save(ordinateur));
            return updated;
        }
        else {
            // Throw new ResourceNotFoundException("Ordinateur", "id", id)) ?
        }
        return null;
    }

    public List<ImprimanteDto> getPrintersByState(int state) {
        List<ImprimanteDto> imprimanteDtoList= new ArrayList<>();
        imprimanteRepo.findByState(state).forEach(
                printer -> {
                    imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                }
        );
        return imprimanteDtoList;
    }

    public List<ImprimanteDto> getAllPrinters() {
        List<ImprimanteDto> imprimanteDtoList= new ArrayList<>();
        imprimanteRepo.findAll().forEach(
                printer -> {
                    imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                }
        );
        return imprimanteDtoList;
    }

    public ImprimanteDto updatePrinterById(Long id, ImprimanteDto PrinterDto) {
        Optional<Printer> currentPrinter = imprimanteRepo.findById(id);

        if(currentPrinter.isPresent()) {
            Printer printer = currentPrinter.get();
            printer.setBrand(PrinterDto.getMarque());
            printer.setState(PrinterDto.getEtat());
            printer.setBarCode(PrinterDto.getCode());
            printer.setPrintSpeed(PrinterDto.getVitesse());
            printer.setResolution(PrinterDto.getResolution());
            printer.setAssignmentDate(PrinterDto.getDateLiv());// DateAffectation ?
            printer.setDeliveryDate(PrinterDto.getDateLiv());
            printer.setWarrantyDate(PrinterDto.getDateGarantie());
            //ordinateur.setTeachers(ordinateurDetails.getEnseignantDto());
            ImprimanteDto updated = ImprimanteDto.toDto(imprimanteRepo.save(printer));
            return updated;
        }
        else {
            // Throw new ResourceNotFoundException("Ordinateur", "id", id)) ?
        }
        return null;
    }



    /*public Ressource updateRessource(final Long ressource_id, Ressource ressourceDetails){
        Optional<Ressource> currentRessource = findRessourceById(ressource_id);

        if(currentRessource.isPresent()) {

            Ressource ressource = currentRessource.get();
            ressource.setCodeBar(ressourceDetails.getCodeBar());
            ressource.setEtat(ressourceDetails.getEtat());
            ressource.setDateAffectation(ressourceDetails.getDateAffectation());
            ressource.setDateLivraison(ressourceDetails.getDateLivraison());
            ressource.setDureeGarantie(ressourceDetails.getDureeGarantie());
            ressource.setEnseignant_id(ressourceDetails.getEnseignant_id());
            return SaveRessource(ressource);
        }
        else return null;

    }*/


}
