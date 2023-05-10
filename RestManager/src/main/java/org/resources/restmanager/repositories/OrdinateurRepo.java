package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdinateurRepo extends JpaRepository<Computer,Long> {
    @Query("SELECT c FROM Computer c, Teacher t WHERE t MEMBER c.teachers AND t.id=:id AND c.state=1")
    public List<Computer> findByEnseignant_Id(@Param("id") Long id);

    public Iterable<Computer> findByState(int state);
}
