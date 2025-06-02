package com.paymedia.jwt_authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {
    private Long id;
    private String policyNumber;
    private String provider;
    private String coverageType;
    private String vehicleRegistrationNumber;

    public InsuranceDTO(Long id, String policyNumber, String provider, String coverageType) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.coverageType = coverageType;
    }
}