package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Failure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureRepository extends JpaRepository<Failure,Long> {
}
