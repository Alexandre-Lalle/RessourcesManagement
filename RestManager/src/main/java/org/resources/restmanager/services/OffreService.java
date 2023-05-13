package org.resources.restmanager.services;


import org.resources.restmanager.model.DTO.mouhsine.ImprimanteDTO;
import org.resources.restmanager.model.DTO.mouhsine.OffreDTO;
import org.resources.restmanager.model.DTO.mouhsine.OrdinateurDTO;
import org.resources.restmanager.model.DTO.mouhsine.ResourceDTO;
import org.resources.restmanager.model.entities.Offre;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Soumission;
import org.resources.restmanager.repositories.OffreRepo;
import org.resources.restmanager.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OffreService {
    @Autowired
    private OffreRepo offreRepo;
    @Autowired
    private ResourcesService ressourceService;
    @Autowired
    private SoumissionService soumissionService;
    @Autowired
    private ResourceRepository resourceRepository;

    public OffreDTO getOffreById(Long offreId) {
        Optional<Offre> offreOptional = offreRepo.findById(offreId);
        return OffreDTO.toDto(offreOptional.get());
    }

    public List<OffreDTO> getAllOffres() {
        List<Offre> offres = offreRepo.findAll();
        List<OffreDTO> offresDTO = new ArrayList<>();

        for(Offre offre : offres){
            offresDTO.add(OffreDTO.toDto(offre));
        }

        return offresDTO;
    }

    public OffreDTO saveOffre(OffreDTO offreDTO) {
        Offre offreEntitie = Offre.toEntity2(offreDTO);
        List<Resource> resourceList = offreEntitie.getResourceList();
        List<Resource> new_resourceList = new ArrayList<>();

        offreEntitie.setResourceList(new_resourceList);
        offreEntitie = offreRepo.save(offreEntitie);
        for ( Resource resource : resourceList){
                resource = ressourceService.getRessourceById(resource.getId());
                resource.setOffre(offreEntitie);
                new_resourceList.add(resource);
        }
        offreEntitie.setResourceList(new_resourceList);
        return OffreDTO.toDto(offreRepo.save(offreEntitie));
    }

    public OffreDTO updateOffre(OffreDTO offre) {
        Offre old_offre = Offre.toEntity2(getOffreById(offre.getId()));
        old_offre.setDateDebut(offre.getDateDebut());
        old_offre.setDateFin(offre.getDateFin());

        Boolean exist = false;
        for (Resource old_resource : old_offre.getResourceList()){
            for (ResourceDTO new_resource : offre.retournerResourceDTOList()){
                if(new_resource.getResourceType() == "Printer"){
                    if (old_resource.getId() == ((ImprimanteDTO)new_resource).getId()){
                        exist = true;
                    }
                }
                if(new_resource.getResourceType() == "Computer"){
                    if (old_resource.getId() == ((OrdinateurDTO)new_resource).getId()){
                        exist = true;
                    }
                }
            }
            ressourceService.deleteOffreFromRessorce(old_resource.getId());
        }
        old_offre.setResourceList(ResourceDTO.toEntitieList(offre.retournerResourceDTOList()));

        return saveOffre(OffreDTO.toDto(old_offre));
    }

    public void deleteOffre(Long offreId) {
        Offre offreOptional = offreRepo.getReferenceById(offreId);
        offreRepo.delete(offreOptional);
    }

    public boolean accepterSoumission(Long id){
        Soumission sm = soumissionService.getSoumissionById(id);
        List<Resource>resources = sm.getOffre().getResourceList();

        for (Resource r:resources) {
            r.setDeliveryDate(sm.getDateLivraison());
            r.setWarrantyDate(sm.getDateGarentie());
        }

        resourceRepository.saveAll(resources);
        return soumissionService.AcceptSoumission(id,sm.getOffre().getId());
    }


}
