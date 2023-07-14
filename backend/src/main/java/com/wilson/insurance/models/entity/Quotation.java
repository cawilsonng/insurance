package com.wilson.insurance.models.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer age;
    private Integer drivingExperience;
    private Integer driverRecord;
    private Integer claims;
    private Integer carValue;
    private Integer annualMileage;
    private Integer insuranceHistory;
    private String carCategory;
    private String carBrand;
    private String carModel;
    private Integer carYear;
    private Integer carPrice;
    private double premium;
    private String quoteReference;
    private boolean isPersonalUse;
    private Date quoteDate;
}
