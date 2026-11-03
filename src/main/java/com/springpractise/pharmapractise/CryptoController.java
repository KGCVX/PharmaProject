package com.springpractise.pharmapractise;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CryptoController {

    private final CryptoRepository repository;

    CryptoController(CryptoRepository repository) {
        this.repository = repository;
    }

    // Create a new record
    @PostMapping("/api/currencies")
    Crypto addCrypto(@RequestBody Crypto newCrypto) {
        return repository.save(newCrypto);
    }

    // Get a specific record
    @GetMapping("/api/currencies/{id}")
    Crypto getCrypto(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new CryptoNotFoundException(id));
    }

    // Get a list of records
    @GetMapping("/api/currencies")
    List<Crypto> getAllCrypto() {
        return repository.findAll();
    }

    // Update a specific record
    @PutMapping("/api/currencies/{id}")
    Crypto updateCrypto(@RequestBody Crypto newCrypto, @PathVariable String id) {

        return repository.findById(id)
                .map(crypto -> {
                    crypto.setName(newCrypto.getName());
                    crypto.setAmount(newCrypto.getAmount());
                    crypto.setMarketCap(newCrypto.getMarketCap());
                    return repository.save(crypto);
                })
                .orElseGet(() -> {
                    return repository.save(newCrypto);
                });
    }

    // Delete a specific record
    @DeleteMapping("/api/currencies/{id}")
    void deleteCrypto(@PathVariable String id) {
        repository.deleteById(id);
    }
}