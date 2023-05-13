package org.resources.restmanager.repositories;

import jakarta.transaction.Transactional;
import org.resources.restmanager.model.entities.Failure;
import org.resources.restmanager.model.entities.Report;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FailureRepository extends JpaRepository<Failure,Long> {
    @Modifying
    @Query("UPDATE Failure  f SET f.processed = :processed WHERE f.id = :id")
    public void updateProcessed(@Param("id") Long id, @Param("processed") boolean processed);

    @Query("SELECT f.resource FROM Failure f WHERE f.id = :id")
    public Resource getResourceByFailureId(@Param("id") Long id);

    @Query("SELECT f.teacher FROM Failure f WHERE f.id = :id")
    public Teacher getTeacherByFailureId(@Param("id") Long id);

    @Query("SELECT f.report FROM Failure f WHERE f.id = :id")
    public Report getReportByFailureId(@Param("id") Long id);
}
