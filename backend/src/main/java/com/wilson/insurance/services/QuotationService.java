package com.wilson.insurance.services;

import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import com.wilson.insurance.models.quotation.carInsurance.ResponseDto;

public interface QuotationService {
    /**
     * Retrieves a car insurance quotation based on the provided request data.
     *
     * @param requestDto The request data containing the necessary information.
     * @return The response containing the car insurance quotation details.
     * @throws UnknownPremiumException If any of the factors are unknown or the driver is going to use the car for commercial or business purposes, throw this exception.
     * @throws ParameterException      If there is an issue with the provided request parameters.
     */
    ResponseDto getCarInsuranceQuotation(RequestDto requestDto) throws UnknownPremiumException, ParameterException;
}
