package com.wilson.insurance.models.quotation.carInsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilson.insurance.models.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResponseDto extends CommonResponse {
    private double premium;
    @JsonProperty("quote_reference")
    private String quoteReference;
}
