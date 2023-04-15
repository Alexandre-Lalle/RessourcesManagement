package org.resources.restmanager.repositories.auth;

import org.resources.restmanager.model.entities.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
