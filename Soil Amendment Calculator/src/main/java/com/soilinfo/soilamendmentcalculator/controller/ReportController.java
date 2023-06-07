package com.soilinfo.soilamendmentcalculator.controller;

import com.soilinfo.soilamendmentcalculator.entity.SoilReport;
import com.soilinfo.soilamendmentcalculator.requestobj.SoilReportRequestObject;
import com.soilinfo.soilamendmentcalculator.responseobj.SoilReportResponseDetails;
import com.soilinfo.soilamendmentcalculator.service.AppUserService;
import com.soilinfo.soilamendmentcalculator.service.SoilReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private AppUserService userService;

    private SoilReportService reportService;

    public ReportController(AppUserService userService, SoilReportService reportService){
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<SoilReportResponseDetails> getReportById(@PathVariable Long reportId){
        SoilReport report = reportService.getSoilReportById(reportId);
        return new ResponseEntity<>(new SoilReportResponseDetails(
                report.getId(),
                report.getLocation(),
                report.getDate(),
                report.getSoilPh(),
                report.getPhosphorus(),
                report.getPotassium(),
                report.getCalcium(),
                report.getMagnesium(),
                report.getZinc(),
                reportService.generateAmendmentRecommendations(report))
        , HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SoilReportResponseDetails> createSoilReport(Authentication authentication,
                             @Valid @RequestBody SoilReportRequestObject reportRequest){
        SoilReport report = reportService.createSoilReport(userService
                        .getAppUserByUsername(authentication.getName()).getId(), reportRequest);
        return new ResponseEntity<>(new SoilReportResponseDetails(
                report.getId(),
                report.getLocation(),
                report.getDate(),
                report.getSoilPh(),
                report.getPhosphorus(),
                report.getPotassium(),
                report.getCalcium(),
                report.getMagnesium(),
                report.getZinc(),
                reportService.generateAmendmentRecommendations(reportRequest))
                , HttpStatus.CREATED);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<String> deleteSoilReport(@PathVariable Long reportId){
        reportService.deleteSoilReport(reportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    

}
