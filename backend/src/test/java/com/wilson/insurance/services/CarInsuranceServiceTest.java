package com.wilson.insurance.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wilson.insurance.constant.CarInsuranceConstants;
import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarInsuranceServiceTest {
    @InjectMocks
    private CarInsuranceServiceImpl carInsuranceService;

    @Mock
    private ApiService apiService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void testSuccessCalculatePremium() throws UnknownPremiumException, ParameterException {
        JsonNode node = objectMapper.createObjectNode();
        ((ObjectNode) node).put("base_premium", "1500.00");
        RequestDto requestDto = RequestDto.builder()
                .age(25).drivingExperience(6)
                .driverRecord(1).claims(0)
                .carValue(50000).annualMileage(10000)
                .insuranceHistory(3).carCategory("SUV")
                .carBrand("Audi").carModel("Q3")
                .carYear(2021).carPrice(50000)
                .isPersonalUse(true)
                .build();
        double expectedPremium = 1470.1500000000003;
        when(apiService.getJsonResource(CarInsuranceConstants.BASE_PREMIUM_JSON_URL)).thenReturn(node);
        double actualPremium = carInsuranceService.calculatePremium(requestDto);
        assertEquals(expectedPremium, actualPremium);
    }

    @Test
    void testInvalidParameterCalculatePremium() {
        JsonNode node = objectMapper.createObjectNode();
        ((ObjectNode) node).put("base_premium", "1500.00");
        RequestDto requestDto = RequestDto.builder()
                .age(25).drivingExperience(6)
                .driverRecord(1).claims(0)
                .carValue(50000).annualMileage(10000)
                .insuranceHistory(3).carCategory("SUV")
                .carBrand("Audi").carModel("Q3")
                .carYear(2021).carPrice(50000)
                .isPersonalUse(null)
                .build();
        assertThrows(ParameterException.class, () ->
                carInsuranceService.calculatePremium(requestDto)
        );
    }

    @Test
    void testUnknownPremiumCalculatePremium() {
        JsonNode node = objectMapper.createObjectNode();
        ((ObjectNode) node).put("base_premium", "1500.00");
        RequestDto requestDto = RequestDto.builder()
                .age(25).drivingExperience(6)
                .driverRecord(1).claims(0)
                .carValue(50000).annualMileage(10000)
                .insuranceHistory(3).carCategory("SUV")
                .carBrand("Audi").carModel("Q3")
                .carYear(2021).carPrice(50000)
                .isPersonalUse(false)
                .build();
        assertThrows(UnknownPremiumException.class, () ->
                carInsuranceService.calculatePremium(requestDto)
        );
    }
}