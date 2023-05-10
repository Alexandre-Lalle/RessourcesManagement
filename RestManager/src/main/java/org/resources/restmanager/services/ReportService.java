package org.resources.restmanager.services;

import jakarta.transaction.Transactional;
import org.resources.restmanager.model.DTO.rachid.ReportDto;
import org.resources.restmanager.model.entities.Report;
import org.resources.restmanager.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportService {
    private final ReportRepository reportRepo;

    @Autowired
    public ReportService(ReportRepository reportRepo) {
        this.reportRepo = reportRepo;

    }


    public List<Report> findAllReports(){
        return reportRepo.findAll();
    }
    public List<ReportDto> getReportInfo(){
        List<ReportDto>  reportDtoList =new ArrayList<>();
        reportRepo.findAll().forEach(
                report -> {
                    ReportDto reportDto= ReportDto.toDto(report);
                    reportDtoList.add(reportDto);
                }
        );
        return reportDtoList;

    }
}
