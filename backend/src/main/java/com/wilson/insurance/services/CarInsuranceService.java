package com.wilson.insurance.services;

import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;

public interface CarInsuranceService {
    /**
     * Calculates the premium for car insurance based on the provided request data.
     *
     * @param requestDto The request data containing the necessary information.
     * @return The calculated premium for the car insurance.
     * @throws UnknownPremiumException If any of the factors are unknown or the driver is going to use the car for commercial or business purposes, throw this exception.
     * @throws ParameterException      If there is an issue with the provided request parameters.
     */
    double calculatePremium(RequestDto requestDto) throws UnknownPremiumException, ParameterException;
}
