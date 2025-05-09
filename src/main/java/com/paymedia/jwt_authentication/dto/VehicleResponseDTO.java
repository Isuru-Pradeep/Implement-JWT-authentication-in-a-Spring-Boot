package com.paymedia.jwt_authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseDTO {
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String color;
}