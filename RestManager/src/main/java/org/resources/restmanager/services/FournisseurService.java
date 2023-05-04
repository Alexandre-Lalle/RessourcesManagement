package org.resources.restmanager.services;


import org.resources.restmanager.model.DTO.mimoune.ImprimanteDto;
import org.resources.restmanager.model.DTO.mimoune.OffreDto;
import org.resources.restmanager.model.DTO.mimoune.OrdinateurDto;
import org.resources.restmanager.model.DTO.mimoune.SoumissionDto;
import org.resources.restmanager.model.entities.Computer;
import org.resources.restmanager.model.entities.Printer;
import org.resources.restmanager.model.entities.Soumission;
import org.resources.restmanager.repositories.OffreRepo;
import org.resources.restmanager.repositories.SoumissionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FournisseurService {
    private final SoumissionRepo soumissionRepo;
    private final OffreRepo offreRepo;

    public FournisseurService(SoumissionRepo soumissionRepo, OffreRepo offreRepo) {
        this.soumissionRepo = soumissionRepo;
        this.offreRepo = offreRepo;
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
                                String className = resource.getClass().getSimpleName();
                                if(className.equals("Printer")) {
                                    Printer printer = (Printer) resource;
                                    if(imprimanteDtoList.size() != 0) {
                                        int size = ordinateurDtoList.size();
                                        for(int i=0;i<size;i++){
                                            ImprimanteDto imprimanteDto = imprimanteDtoList.get(i);
                                            if (ImprimanteDto.equals(imprimanteDto, printer)) {
                                                int qty = imprimanteDto.getQty();
                                                imprimanteDto.setQty(qty + 1);
                                            } else imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                                        };
                                    }else imprimanteDtoList.add(ImprimanteDto.toDto(printer));
                                }else{
                                    Computer computer = (Computer) resource;
                                    if(ordinateurDtoList.size() != 0) {
                                        int size = ordinateurDtoList.size();
                                        for(int i=0;i<size;i++){
                                            System.out.println("the size is"+ordinateurDtoList.size());
                                            OrdinateurDto ordinateurDto = ordinateurDtoList.get(i);
                                            if (OrdinateurDto.equals(ordinateurDto, computer)) {
                                                int qty = ordinateurDto.getQty();
                                                ordinateurDto.setQty(qty + 1);
                                            } else ordinateurDtoList.add(OrdinateurDto.toDto(computer));

                                        }
                                    }else ordinateurDtoList.add(OrdinateurDto.toDto(computer));

                                }
                            }
                    );

                    offreDto.setOrdinateurDtoList(ordinateurDtoList);
                    offreDto.setImprimanteDtoList(imprimanteDtoList);
                    offreDtoList.add(offreDto);
                }
        );
        return offreDtoList;

    }
    public Soumission addSoumission(SoumissionDto soumissionDto){
        soumissionDto.setEtat(0);
        return soumissionRepo.save(Soumission.toEntity(soumissionDto)) ;
    }

    public List<SoumissionDto> getSoumissions(long enseignant_id){
        List<SoumissionDto> soumissionDtoList= new ArrayList<>();
        soumissionRepo.findSoumissionsByFournisseurS_Id(enseignant_id).forEach(
                soumission  -> {
                    soumissionDtoList.add(SoumissionDto.toDto(soumission));
                }
        );
        return soumissionDtoList;
    }

}
