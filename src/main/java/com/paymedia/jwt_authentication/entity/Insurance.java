package com.paymedia.jwt_authentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "insurances")
@Getter
@Setter
@NoArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private String provider;
    private String coverageType;
    private String vehicleRegistrationNumber;

    public Insurance(Long id, String policyNumber, String provider, String coverageType, String vehicleRegistrationNumber) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.coverageType = coverageType;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public Insurance(String policyNumber, String provider, String coverageType) {
        this.policyNumber=policyNumber;
        this.provider =provider;
        this.coverageType =coverageType;
    }

    public Insurance(String policyNumber, String provider, String coverageType, String vehicleRegistrationNumber) {
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.coverageType = coverageType;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }
}
