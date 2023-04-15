package org.resources.restmanager.repositories.auth;

import org.resources.restmanager.model.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String email);
    boolean existsByUserName(String email);
    public User findByEmail(String email);
}
