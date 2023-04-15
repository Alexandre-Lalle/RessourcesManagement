package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    public Report findByFailure_Id(Long id);
}
