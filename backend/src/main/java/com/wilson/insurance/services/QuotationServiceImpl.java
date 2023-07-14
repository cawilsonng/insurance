package com.wilson.insurance.services;

import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.entity.Quotation;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import com.wilson.insurance.models.quotation.carInsurance.ResponseDto;
import com.wilson.insurance.repositories.QuotationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {
    private final QuotationRepository quotationRepository;
    private final CarInsuranceService carInsuranceService;

    public QuotationServiceImpl(CarInsuranceService carInsuranceService, QuotationRepository quotationRepository) {
        this.carInsuranceService = carInsuranceService;
        this.quotationRepository = quotationRepository;
    }

    public ResponseDto getCarInsuranceQuotation(RequestDto requestDto) throws UnknownPremiumException, ParameterException {
        double premium = carInsuranceService.calculatePremium(requestDto);
        String referenceNumber = UUID.randomUUID().toString();
        Quotation quotation = Quotation.builder()
                .age(requestDto.getAge()).annualMileage(requestDto.getAnnualMileage())
                .carBrand(requestDto.getCarBrand()).carCategory(requestDto.getCarCategory())
                .carModel(requestDto.getCarModel()).carPrice(requestDto.getCarPrice())
                .carYear(requestDto.getCarYear()).carValue(requestDto.getCarValue())
                .claims(requestDto.getClaims()).driverRecord(requestDto.getDriverRecord())
                .drivingExperience(requestDto.getDrivingExperience()).insuranceHistory(requestDto.getInsuranceHistory())
                .premium(premium).quoteReference(referenceNumber)
                .isPersonalUse(requestDto.getIsPersonalUse())
                .quoteDate(new Date())
                .build();
        quotationRepository.save(quotation);
        return ResponseDto.builder().success(true).premium(premium).quoteReference(referenceNumber).build();
    }
}
