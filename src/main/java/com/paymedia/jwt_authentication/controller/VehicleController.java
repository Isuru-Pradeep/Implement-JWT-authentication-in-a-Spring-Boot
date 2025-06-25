package com.paymedia.jwt_authentication.controller;

import com.paymedia.jwt_authentication.dto.InsuranceDTO;
import com.paymedia.jwt_authentication.dto.VehicleDTO;
import com.paymedia.jwt_authentication.dto.VehicleResponseDTO;
import com.paymedia.jwt_authentication.service.InsuranceWebClientService;
import com.paymedia.jwt_authentication.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @Autowired
    InsuranceWebClientService insuranceClientService;

    @PostMapping
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleResponseDTO responseDTO = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasPermission('TEST','READ')")
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        List<VehicleResponseDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id) {
        VehicleResponseDTO responseDTO = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(responseDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE')")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @PathVariable Long id,@RequestBody VehicleDTO vehicleDTO) {
        VehicleResponseDTO responseDTO = vehicleService.updateVehicle(id, vehicleDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register-insurance")
    public ResponseEntity<InsuranceDTO> registerInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        System.out.println("called register controller");
        InsuranceDTO response = insuranceClientService.createInsurance(insuranceDTO);
        return ResponseEntity.ok(response);
    }
}