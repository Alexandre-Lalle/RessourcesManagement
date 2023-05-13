package org.resources.restmanager.services;

import jakarta.transaction.Transactional;
import org.resources.restmanager.model.DTO.rachid.DemandeRetourDto;
import org.resources.restmanager.model.entities.DemandeRetour;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.repositories.DemandeRetourRepository;
import org.resources.restmanager.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DemandeRetourservice {
    private DemandeRetourRepository demandeRetourRepository;
    private ResourceRepository resourceRepository;
    @Autowired
    public DemandeRetourservice(DemandeRetourRepository demandeRetourRepository, ResourceRepository resourceRepository) {
        this.demandeRetourRepository = demandeRetourRepository;
        this.resourceRepository = resourceRepository;
    }
    public DemandeRetourDto addDemandeRetour(DemandeRetourDto demandeRetour){
        Resource r = resourceRepository.getReferenceById(demandeRetour.getResource_id());
        System.out.println(DemandeRetour.toEntity(demandeRetour, r));
        return DemandeRetourDto.toDto(demandeRetourRepository.save(DemandeRetour.toEntity(demandeRetour, r)));

    }
    @Autowired
    public List<DemandeRetour> findAllDemandes(){
        return demandeRetourRepository.findAll();
    }

//    public DemandeRetour findDemandeByRId(Long resource_id) {
//        List<DemandeRetour> demandes = demandeRetourRepository.findAll();
//        DemandeRetour res = null;
//        for (DemandeRetour demande1 : demandes) {
//            if (demande1.getResource_id().equals(resource_id)) {
//                res = demande1; // Set to true if a match is found
//                break; // Break out of the loop when a match is found
//            }
//        }
//        return res;
//    }
}
