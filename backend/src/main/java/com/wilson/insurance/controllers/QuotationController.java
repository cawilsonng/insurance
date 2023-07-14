package com.wilson.insurance.controllers;

import com.wilson.insurance.constant.ControllerConstants;
import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.quotation.carInsurance.RequestDto;
import com.wilson.insurance.models.quotation.carInsurance.ResponseDto;
import com.wilson.insurance.services.QuotationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConstants.Quotation.BASE)
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping(ControllerConstants.Quotation.CAR_QUOTE)
    public ResponseDto CarInsuranceQuote(@RequestBody RequestDto requestDto) throws UnknownPremiumException, ParameterException {
        return quotationService.getCarInsuranceQuotation(requestDto);
    }
}
