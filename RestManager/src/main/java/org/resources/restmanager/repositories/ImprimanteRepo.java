package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  ImprimanteRepo extends JpaRepository<Printer, Long> {
    @Query("SELECT p FROM Printer p, Teacher t WHERE t MEMBER p.teachers AND t.id=:id AND p.state=1")
    public List<Printer> findByEnseignant_Id(@Param("id") Long id);

    public Iterable<Printer> findByState(int state);
}
