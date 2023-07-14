package com.wilson.insurance.models.quotation.carInsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    private Integer age;
    @JsonProperty("driving_experience")
    private Integer drivingExperience;
    @JsonProperty("driver_record")
    private Integer driverRecord;
    private Integer claims;
    @JsonProperty("car_value")
    private Integer carValue;
    @JsonProperty("annual_mileage")
    private Integer annualMileage;
    @JsonProperty("insurance_history")
    private Integer insuranceHistory;
    //Addition field
    @JsonProperty("car_category")
    private String carCategory;
    @JsonProperty("car_brand")
    private String carBrand;
    @JsonProperty("car_model")
    private String carModel;
    @JsonProperty("car_year")
    private Integer carYear;
    @JsonProperty("car_price")
    private Integer carPrice;
    @JsonProperty("is_personal_use")
    private Boolean isPersonalUse;
}
