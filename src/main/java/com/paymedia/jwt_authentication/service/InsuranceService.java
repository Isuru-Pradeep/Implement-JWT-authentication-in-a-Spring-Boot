package com.paymedia.jwt_authentication.service;

import com.paymedia.jwt_authentication.dto.InsuranceDTO;
import com.paymedia.jwt_authentication.entity.Insurance;
import com.paymedia.jwt_authentication.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository repository;

    public InsuranceDTO create(InsuranceDTO dto) {
        Insurance insurance = new Insurance(dto.getPolicyNumber(), dto.getProvider(), dto.getCoverageType() , dto.getVehicleRegistrationNumber());
        return toDto(repository.save(insurance));
    }

    public List<InsuranceDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public InsuranceDTO findById(Long id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public InsuranceDTO update(Long id, InsuranceDTO dto) {
        Insurance insurance = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        insurance.setPolicyNumber(dto.getPolicyNumber());
        insurance.setProvider(dto.getProvider());
        insurance.setCoverageType(dto.getCoverageType());
        insurance.setVehicleRegistrationNumber(dto.getVehicleRegistrationNumber());
        return toDto(repository.save(insurance));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private InsuranceDTO toDto(Insurance entity) {
        return new InsuranceDTO(entity.getId(), entity.getPolicyNumber(), entity.getProvider(), entity.getCoverageType());
    }
}
