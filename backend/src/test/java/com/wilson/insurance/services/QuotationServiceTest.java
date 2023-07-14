package com.wilson.insurance.services;

import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.entity.Quotation;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import com.wilson.insurance.repositories.QuotationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuotationServiceTest {
    @InjectMocks
    private QuotationServiceImpl quotationService;

    @Mock
    private QuotationRepository quotationRepository;
    @Mock
    private CarInsuranceService carInsuranceService;

    @Test
    void testSaveQuotation() throws UnknownPremiumException, ParameterException {
        UUID uuid = UUID.randomUUID();
        RequestDto requestDto = RequestDto.builder()
                .age(25).drivingExperience(6)
                .driverRecord(1).claims(0)
                .carValue(50000).annualMileage(10000)
                .insuranceHistory(3).carCategory("SUV")
                .carBrand("Audi").carModel("Q3")
                .carYear(2021).carPrice(50000)
                .isPersonalUse(true)
                .build();
        Date expectedDate = new Date();
        Quotation quotation = Quotation.builder()
                .age(25).drivingExperience(6)
                .driverRecord(1).claims(0)
                .carValue(50000).annualMileage(10000)
                .insuranceHistory(3).carCategory("SUV")
                .carBrand("Audi").carModel("Q3")
                .carYear(2021).carPrice(50000)
                .premium(1500.0).isPersonalUse(true)
                .quoteReference(uuid.toString())
                .quoteDate(expectedDate)
                .build();
        mockStatic(UUID.class);
        when(carInsuranceService.calculatePremium(requestDto)).thenReturn(1500.0);
        when(UUID.randomUUID()).thenReturn(uuid);
        when(quotationRepository.save(any(Quotation.class))).thenReturn(quotation);
        quotationService.getCarInsuranceQuotation(requestDto);
        verify(quotationRepository).save(any(Quotation.class));
    }
}