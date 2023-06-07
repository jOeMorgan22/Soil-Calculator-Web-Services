package com.soilinfo.soilamendmentcalculator.service;

import com.soilinfo.soilamendmentcalculator.entity.AppUser;
import com.soilinfo.soilamendmentcalculator.entity.SoilReport;
import com.soilinfo.soilamendmentcalculator.exception.AppUserNotFoundException;
import com.soilinfo.soilamendmentcalculator.exception.SoilReportNotFoundException;
import com.soilinfo.soilamendmentcalculator.repository.AppUserRepository;
import com.soilinfo.soilamendmentcalculator.repository.SoilReportRepository;
import com.soilinfo.soilamendmentcalculator.requestobj.SoilReportRequestObject;
import com.soilinfo.soilamendmentcalculator.responseobj.SoilReportResponseDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoilReportServiceImpl implements SoilReportService{

    private final SoilReportRepository reportRepository;

    private final CalculationService calculationService;

    private final AppUserRepository userRepository;

    public SoilReportServiceImpl(AppUserRepository userRepository, SoilReportRepository reportRepository,
                                 CalculationService calculationService){
        this.reportRepository = reportRepository;
        this.calculationService = calculationService;
        this.userRepository = userRepository;
    }

    @Override
    public SoilReport getSoilReportById(Long id) {
        Optional<SoilReport> soilReport = reportRepository.findSoilReportById(id);
        if(soilReport.isPresent()){
            return reportRepository.findSoilReportById(id).get();
        }
        throw new SoilReportNotFoundException();
    }

    @Override
    public List<SoilReport> getSoilReportsByUserId(Long userId) {
        List<SoilReport> soilReports = reportRepository.findSoilReportByUserId(userId);
        if(soilReports.isEmpty()){
            throw new SoilReportNotFoundException();
        }
        return new ArrayList<>(soilReports);
    }

    @Override
    public SoilReport createSoilReport(Long id, SoilReportRequestObject reportRequest) throws AppUserNotFoundException {
        SoilReport.SoilReportBuilder soilReportBuilder = new SoilReport.SoilReportBuilder();
        Optional<AppUser> appUser = userRepository.findAppUserById(id);
        if(appUser.isEmpty()){
            throw new AppUserNotFoundException(id);
        }
        return reportRepository.save(
                soilReportBuilder
                        .user(userRepository.findAppUserById(id).get())
                        .location(reportRequest.location())
                        .date()
                        .soilPh(reportRequest.soilPh())
                        .phosphorus(reportRequest.phosphorus())
                        .potassium(reportRequest.potassium())
                        .calcium(reportRequest.calcium())
                        .magnesium(reportRequest.magnesium())
                        .zinc(reportRequest.zinc())
                        .build()
                        );
    }

    @Override
    public void deleteSoilReport(Long id) throws DataIntegrityViolationException {
        reportRepository.deleteById(id);
    }

    @Override
    public String generateAmendmentRecommendations(SoilReportRequestObject reportRequest) {
        return  "Recommend adding 10 pounds of Nitrogen equivalent per 1000 sq. ft. "
                + System.lineSeparator() +
                calculationService.calculatePhAmendment(reportRequest.soilPh())
                + System.lineSeparator() +
                calculationService.calculatePhosphorusAmendment(reportRequest.phosphorus())
                + System.lineSeparator() +
                calculationService.calculatePotassiumAmendment(reportRequest.potassium())
                + System.lineSeparator() +
                calculationService.calculateMagnesiumAmendment(reportRequest.magnesium())
                + System.lineSeparator() +
                calculationService.calculateZincAmendment(reportRequest.zinc());
    }

    @Override
    public String generateAmendmentRecommendations(SoilReport soilReport) {
        return  "Recommend adding 10 pounds of Nitrogen equivalent per 1000 sq. ft. "
                + System.lineSeparator() +
                calculationService.calculatePhAmendment(soilReport.getSoilPh())
                + System.lineSeparator() +
                calculationService.calculatePhosphorusAmendment(soilReport.getPhosphorus())
                + System.lineSeparator() +
                calculationService.calculatePotassiumAmendment(soilReport.getPotassium())
                + System.lineSeparator() +
                calculationService.calculateMagnesiumAmendment(soilReport.getMagnesium())
                + System.lineSeparator() +
                calculationService.calculateZincAmendment(soilReport.getZinc());
    }
}
