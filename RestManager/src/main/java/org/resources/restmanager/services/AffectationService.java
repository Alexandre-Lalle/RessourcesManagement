package org.resources.restmanager.services;

import org.resources.restmanager.model.DTO.lalle.AffectationDto;
import org.resources.restmanager.model.entities.Affectation;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.repositories.AffectationRepository;
import org.resources.restmanager.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffectationService {

    @Autowired
    private AffectationRepository affectationRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    // Fonction pour créer une nouvelle affectation
    public AffectationDto createAffectation(AffectationDto affectationDto) {
        Resource r = resourceRepository.getReferenceById(affectationDto.getResourceId());
        Affectation affectation = AffectationDto.toEntity(affectationDto, r);
        // Mettre l'état à disponible
        affectation.getResource().setState(1);
        return affectationDto.toDto(affectationRepository.save(affectation));
    }

    // Fonction pour récupérer une affectation par son ID
    public AffectationDto getAffectationById(Long id) {
        return AffectationDto.toDto(affectationRepository.findById(id).orElse(null));
    }

    // Fonction pour supprimer une affectation existante par son ID
    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }

    // Fonction pour mettre à jour une affectation existante
    public AffectationDto updateAffectation(AffectationDto affectationDto) {
        Affectation existingAffectation = affectationRepository.findById(affectationDto.getId()).orElse(null);
        if (existingAffectation != null) {
            Resource r  = resourceRepository.getReferenceById(affectationDto.getResourceId());
            Affectation map = AffectationDto.toEntity(affectationDto, r);
            existingAffectation.setTeacherList(map.getTeacherList());
            existingAffectation.setResource(map.getResource());
            existingAffectation.setDateAffectation(map.getDateAffectation());
            // Mettre l'état à disponible
            existingAffectation.getResource().setState(1);

            return affectationDto.toDto(affectationRepository.save(existingAffectation));
        }
        return null;
    }

    public AffectationDto getAffectationByResourceId(Long resourceId) {
        Affectation affectation = affectationRepository.findByResourceId(resourceId);
        if(affectation == null) {
            //throw new NotFoundException("Affectation not found with resource id : " + resourceId);
            return null;
        }
        return AffectationDto.toDto(affectation);
    }

}
