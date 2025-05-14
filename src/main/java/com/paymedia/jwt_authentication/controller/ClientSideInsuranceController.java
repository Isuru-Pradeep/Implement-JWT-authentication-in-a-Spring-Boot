package com.paymedia.jwt_authentication.controller;

import com.paymedia.jwt_authentication.dto.InsuranceDTO;
import com.paymedia.jwt_authentication.service.InsuranceWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-insurance")
@RequiredArgsConstructor
public class ClientSideInsuranceController {
    private final InsuranceWebClientService client;

    @PostMapping
    public InsuranceDTO create(@RequestBody InsuranceDTO dto) {
        return client.createInsurance(dto);
    }

    @GetMapping
    public List<InsuranceDTO> getAll() {
        return client.getAllInsurances();
    }

    @GetMapping("/{id}")
    public InsuranceDTO getById(@PathVariable Long id) {
        return client.getInsuranceById(id);
    }

    @PutMapping("/{id}")
    public InsuranceDTO update(@PathVariable Long id, @RequestBody InsuranceDTO dto) {
        return client.updateInsurance(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        client.deleteInsurance(id);
        return ResponseEntity.noContent().build();
    }
}
