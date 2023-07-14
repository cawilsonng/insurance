package com.wilson.insurance.services;

import com.wilson.insurance.constant.CarInsuranceConstants;
import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
@Log
public class CarInsuranceServiceImpl implements CarInsuranceService {
    private final ApiService apiService;

    public CarInsuranceServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public double calculatePremium(RequestDto requestDto) throws UnknownPremiumException, ParameterException {
        validateRequestDto(requestDto);
        double ageFactor = getFactor(CarInsuranceConstants.ageFactorMap, requestDto.getAge());
        double drivingExpFactor = getFactor(CarInsuranceConstants.drivingExpFactorMap, requestDto.getDrivingExperience());
        double driverRecordFactor = getFactor(CarInsuranceConstants.driverRecordFactorMap, requestDto.getDriverRecord());
        double claimRecordFactor = getFactor(CarInsuranceConstants.claimRecordFactorMap, requestDto.getClaims());
        double carValueFactor = getFactor(CarInsuranceConstants.carValueFactorMap, requestDto.getCarValue());
        double mileageFactor = getFactor(CarInsuranceConstants.mileageFactorMap, requestDto.getAnnualMileage());
        double insuranceHistoryFactor = getFactor(CarInsuranceConstants.insuranceHistoryFactorMap, requestDto.getInsuranceHistory());
        if (requestDto.getIsPersonalUse() != true) {
            throw new UnknownPremiumException();
        }
        double premium = getBasePremium() * ageFactor * drivingExpFactor * driverRecordFactor *
                claimRecordFactor * carValueFactor * mileageFactor * insuranceHistoryFactor;
        return premium;
    }

    private double getFactor(TreeMap<Integer, Double> factorMap, int amount) throws UnknownPremiumException {
        Double factor = CarInsuranceConstants.mappedValue(factorMap, amount);
        if (factor == null) {
            throw new UnknownPremiumException();
        }
        return factor;
    }

    private void validateRequestDto(RequestDto requestDto) throws ParameterException {
        if (requestDto.getAge() == null || requestDto.getAnnualMileage() == null ||
                requestDto.getCarValue() == null || requestDto.getClaims() == null ||
                requestDto.getDriverRecord() == null || requestDto.getDrivingExperience() == null ||
                requestDto.getInsuranceHistory() == null ||
                requestDto.getCarBrand() == null || requestDto.getCarCategory() == null ||
                requestDto.getCarModel() == null || requestDto.getCarPrice() == null ||
                requestDto.getCarYear() == null || requestDto.getIsPersonalUse() == null ||
                requestDto.getAge() <= 0 || requestDto.getCarValue() < 0 ||
                requestDto.getAnnualMileage() < 0 || requestDto.getClaims() < 0 ||
                requestDto.getDriverRecord() < 0 || requestDto.getDrivingExperience() < 0 ||
                requestDto.getInsuranceHistory() < 0) {
            throw new ParameterException();
        }
    }

    public double getBasePremium() {
        return apiService.getJsonResource(CarInsuranceConstants.BASE_PREMIUM_JSON_URL)
                .path(CarInsuranceConstants.BASE_PREMIUM_FIELD_NAME).asDouble();
    }

}
