package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
