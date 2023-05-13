package org.resources.restmanager.repositories;


import org.resources.restmanager.model.entities.Soumission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoumissionRepo extends JpaRepository<Soumission,Long> {
    List<Soumission> findSoumissionsByFournisseurS_IdOrderByIdDesc(long fournisseur_id);

    @Query("SELECT s FROM Soumission s WHERE s.offre.id =:id")
    List<Soumission> findSoumissionInOffre(@Param("id") Long id);
}
