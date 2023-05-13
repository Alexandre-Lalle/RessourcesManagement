package org.resources.restmanager.repositories.auth;

import org.resources.restmanager.model.entities.Provider;
import org.resources.restmanager.model.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AppUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String email);
    boolean existsByUserName(String email);
    public User findByEmail(String email);
    List<Provider> findAllByRoles_Name(String roleName);
    void deleteProviderById(Long id);
}
