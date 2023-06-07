package com.soilinfo.soilamendmentcalculator.service;

import com.soilinfo.soilamendmentcalculator.entity.SoilReport;
import com.soilinfo.soilamendmentcalculator.requestobj.SoilReportRequestObject;
import com.soilinfo.soilamendmentcalculator.responseobj.SoilReportResponseDetails;

import java.util.List;

public interface SoilReportService {

    SoilReport getSoilReportById(Long id);
    List<SoilReport> getSoilReportsByUserId(Long userId);
    SoilReport createSoilReport(Long id, SoilReportRequestObject reportRequest);
    void deleteSoilReport(Long id);
    String generateAmendmentRecommendations(SoilReportRequestObject reportRequest);

    public String generateAmendmentRecommendations(SoilReport soilReport);

}
