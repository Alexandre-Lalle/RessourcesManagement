package org.resources.restmanager.services;


import org.resources.restmanager.model.DTO.mimoune.*;
import org.resources.restmanager.model.entities.Computer;
import org.resources.restmanager.model.entities.Printer;
import org.resources.restmanager.model.entities.Soumission;
import org.resources.restmanager.repositories.DemandeRetourRepository;
import org.resources.restmanager.repositories.OffreRepo;
import org.resources.restmanager.repositories.SoumissionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FournisseurService {
    private final SoumissionRepo soumissionRepo;
    private final OffreRepo offreRepo;
    private final DemandeRetourRepository demandeRetourRepository;

    public FournisseurService(SoumissionRepo soumissionRepo, OffreRepo offreRepo, DemandeRetourRepository demandeRetourRepository) {
        this.soumissionRepo = soumissionRepo;
        this.offreRepo = offreRepo;
        this.demandeRetourRepository = demandeRetourRepository;
    }
    public List<OffreDto> getOffres() {
        List<OffreDto>  offreDtoList =new ArrayList<>();
        offreRepo.findAll().forEach(
                offre -> {
                    OffreDto offreDto = OffreDto.toDto(offre);
                    List<OrdinateurDto>ordinateurDtoList = new ArrayList<>();
                    List<ImprimanteDto>imprimanteDtoList = new ArrayList<>();
                    offre.getResourceList().forEach(
                            resource -> {
                                System.out.println("resource : "+resource);
                                String className = resource.getClass().getSimpleName();
                                if(className.equals("Printer")) {
                                    Printer printer = (Printer) resource;
                                    if(imprimanteDtoList.size() != 0) {
                                        int size = imprimanteDtoList.size();
                                        for(int i=0;i<size;i++){
                                            ImprimanteDto imprimanteDto = imprimanteDtoList.get(i);
                                            if (ImprimanteDto.equals(imprimanteDto, printer)) {
                                                int qty = imprimanteDto.getQty();
                                                imprimanteDto.setQty(qty + 1);
                                            } else {

                                                imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                                            }
                                        };
                                    }else imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                                }else{
                                    System.out.println("test 1");
                                    Computer computer = (Computer) resource;
                                    System.out.println(computer);
                                    if(ordinateurDtoList.size() != 0) {
                                        int size = ordinateurDtoList.size();
                                        for(int i=0;i<size;i++){
                                            OrdinateurDto ordinateurDto = ordinateurDtoList.get(i);
                                            if (OrdinateurDto.equals(ordinateurDto, computer)) {
                                                int qty = ordinateurDto.getQty();
                                                ordinateurDto.setQty(qty + 1);
                                            } else ordinateurDtoList.add(OrdinateurDto.toDto(computer));

                                        }
                                    }else {
                                        System.out.println("3");
                                        ordinateurDtoList.add(OrdinateurDto.toDto(computer));
                                    }

                                }
                            }
                    );

                    offreDto.setOrdinateurDtoList(ordinateurDtoList);
                    offreDto.setImprimanteDtoList(imprimanteDtoList);
                    offreDtoList.add(offreDto);
                }
        );
        System.out.println("offreDto : "+offreDtoList);
        return offreDtoList;

    }
    public SoumissionDto addSoumission(SoumissionDto soumissionDto){
        soumissionDto.setEtat(0);
        return SoumissionDto.toDto(soumissionRepo.save(Soumission.toEntity(soumissionDto)) ) ;
    }
    public SoumissionDto modifySoumission(SoumissionDto soumissionDto){
        Soumission soumission = Soumission.toEntity(soumissionDto);
        soumission.setId(soumissionDto.getId());
        return SoumissionDto.toDto(soumissionRepo.save(soumission)) ;
    }

    public List<SoumissionDto> getSoumissions(long fournisseur_id){

        List<SoumissionDto> soumissionDtoList= new ArrayList<>();
        soumissionRepo.findSoumissionsByFournisseurS_IdOrderByIdDesc(fournisseur_id).forEach(
                soumission  -> {
                    soumissionDtoList.add(SoumissionDto.toDto(soumission));
                }
        );
        return soumissionDtoList;
    }

    public void deleteSoumission(Long soumissionId) {
          soumissionRepo.deleteById(soumissionId);
    }

    public List<DemandeRetourDto> getMessages(Long fournisseurId) {
        List<DemandeRetourDto> demandeRetourDtoList = new ArrayList<>();
        demandeRetourRepository.findAllDemandRetoursByProvider(fournisseurId)
                .forEach(
                        demandeRetour -> {
                            String className = demandeRetour.getResource().getClass().getSimpleName();
                            DemandeRetourDto demandeRetourDto = DemandeRetourDto.toDto(demandeRetour);

                            if( className.equals("Printer")) demandeRetourDto.setImprimanteDto(ImprimanteDto.toDto((Printer)demandeRetour.getResource()));
                            else demandeRetourDto.setOrdinateurDto(OrdinateurDto.toDto((Computer)demandeRetour.getResource()));
                            demandeRetourDtoList.add(demandeRetourDto) ;
                        }
                );
        return  demandeRetourDtoList;
    }
}
