package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.DemandeRetour;
import org.resources.restmanager.model.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeRetourRepository extends JpaRepository<DemandeRetour,Long> {
    @Query("SELECT dr FROM DemandeRetour dr,Resource rc,Provider pr WHERE dr.resource.id =rc.id and  rc.provider.id =:provider_id")
    public List<DemandeRetour> findAllDemandRetoursByProvider(@Param("provider_id") long provider_id);
}
