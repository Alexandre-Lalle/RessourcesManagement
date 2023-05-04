package org.resources.restmanager.repositories;


import org.resources.restmanager.model.entities.Soumission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoumissionRepo extends JpaRepository<Soumission,Long> {
    List<Soumission> findSoumissionsByFournisseurS_Id(long fournisseur_id);
}
