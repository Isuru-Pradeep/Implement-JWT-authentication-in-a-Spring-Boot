package com.paymedia.jwt_authentication.service;

import com.paymedia.jwt_authentication.dto.InsuranceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceWebClientService {
    private final WebClient webClient;

    public InsuranceDTO createInsurance(InsuranceDTO dto) {
        return webClient.post()
                .uri("/insurance")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(InsuranceDTO.class)
                .block(); //testing purpose I used block here when production .subscribe() use for asyn properties
    }

    public List<InsuranceDTO> getAllInsurances() {
        return webClient.get()
                .uri("/insurance")
                .retrieve()
                .bodyToFlux(InsuranceDTO.class)
                .collectList()
                .block();
    }

    public InsuranceDTO getInsuranceById(Long id) {
        return webClient.get()
                .uri("/insurance/{id}", id)
                .retrieve()
                .bodyToMono(InsuranceDTO.class)
                .block();
    }

    public InsuranceDTO updateInsurance(Long id, InsuranceDTO dto) {
        return webClient.put()
                .uri("/insurance/{id}", id)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(InsuranceDTO.class)
                .block();
    }

    public void deleteInsurance(Long id) {
        webClient.delete()
                .uri("/insurance/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}

