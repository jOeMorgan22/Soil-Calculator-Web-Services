package com.soilinfo.soilamendmentcalculator.repository;

import com.soilinfo.soilamendmentcalculator.entity.SoilReport;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SoilReportRepository extends CrudRepository<SoilReport, Long> {
    Optional<SoilReport> findSoilReportById(Long id);
    List<SoilReport> findSoilReportByUserId(Long id);
    Optional<SoilReport> findSoilReportByDate(LocalDateTime date);
}
