package com.aios.providerservice.controller;

import com.aios.providerservice.entity.Provider;
import com.aios.providerservice.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // CREATE provider
    @PostMapping
    public ResponseEntity<Provider> createProvider(
            @RequestBody Provider provider) {

        Provider savedProvider = providerService.createProvider(provider);

        return ResponseEntity.ok(savedProvider);
    }

    // GET all providers
    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {

        return ResponseEntity.ok(
                providerService.getAllProviders()
        );
    }

    // GET provider by ID
    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(
            @PathVariable Long id) {

        return providerService.getProviderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE provider
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(
            @PathVariable Long id,
            @RequestBody Provider provider) {

        return ResponseEntity.ok(
                providerService.updateProvider(id, provider)
        );
    }

    // DELETE provider
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(
            @PathVariable Long id) {

        providerService.deleteProvider(id);

        return ResponseEntity.noContent().build();
    }
}