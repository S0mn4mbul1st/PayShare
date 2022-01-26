package com.payshare.server.controller;

import com.payshare.server.repository.CurrencyRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    @NonNull
    private final CurrencyRepository currencyRepository;

    @GetMapping
    public ResponseEntity<?> getAllCurrencies() {
        return ResponseEntity.ok(currencyRepository.findAll());
    }
}
