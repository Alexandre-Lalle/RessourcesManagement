package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {
    Affectation findByResourceId(Long resourceId);
}
