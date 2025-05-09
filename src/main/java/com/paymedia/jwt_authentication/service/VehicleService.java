package com.paymedia.jwt_authentication.service;

import com.paymedia.jwt_authentication.dto.VehicleDTO;
import com.paymedia.jwt_authentication.dto.VehicleResponseDTO;
import com.paymedia.jwt_authentication.entity.Vehicle;
import com.paymedia.jwt_authentication.exceptions.VehicleNotFoundException;
import com.paymedia.jwt_authentication.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public VehicleResponseDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setColor(vehicleDTO.getColor());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToResponseDTO(savedVehicle);
    }

    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public VehicleResponseDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        return convertToResponseDTO(vehicle);
    }

    public VehicleResponseDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));

        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setColor(vehicleDTO.getColor());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return convertToResponseDTO(updatedVehicle);
    }

    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException(id);
        }
        vehicleRepository.deleteById(id);
    }

    private VehicleResponseDTO convertToResponseDTO(Vehicle vehicle) {
        VehicleResponseDTO responseDTO = new VehicleResponseDTO();
        responseDTO.setId(vehicle.getId());
        responseDTO.setMake(vehicle.getMake());
        responseDTO.setModel(vehicle.getModel());
        responseDTO.setYear(vehicle.getYear());
        responseDTO.setColor(vehicle.getColor());
        return responseDTO;
    }
}