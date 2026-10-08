package com.aios.providerservice.service;

import com.aios.providerservice.entity.Provider;
import com.aios.providerservice.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    // Create provider
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    // Get all providers
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // Get provider by ID
    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    // Update provider
    public Provider updateProvider(Long id, Provider provider) {

        Provider existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        existingProvider.setName(provider.getName());
        existingProvider.setPhone(provider.getPhone());
        existingProvider.setEmail(provider.getEmail());
        existingProvider.setService(provider.getService());
        existingProvider.setExperience(provider.getExperience());
        existingProvider.setPrice(provider.getPrice());
        existingProvider.setAvailability(provider.getAvailability());
        existingProvider.setArea(provider.getArea());

        return providerRepository.save(existingProvider);
    }

    // Delete provider
    public void deleteProvider(Long id) {

        if (!providerRepository.existsById(id)) {
            throw new RuntimeException("Provider not found");
        }

        providerRepository.deleteById(id);
    }
}