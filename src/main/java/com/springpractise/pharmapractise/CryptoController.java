package com.springpractise.pharmapractise;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CryptoController {

    private static final Logger logger = LoggerFactory.getLogger(CryptoController.class);

    private final CryptoRepository repository;

    CryptoController(CryptoRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new record for a cryptocurrency in the in-memory H2 database based on the new information provided in
     * the JSON.
     *
     * @param newCrypto (Crypto): The provided JSON that describes a cryptocurrency is loaded into a custom data type.
     *                  It is used to describe the new cryptocurrency.
     * @param request (HttpServletRequest): HTTP request containing information about the client.
     * @return The created Crypto object after it has been saved in the in-memory H2 database.
     */
    @PostMapping("/api/currencies")
    Crypto addCrypto(@RequestBody Crypto newCrypto, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        logger.info("{} created the crypto currency: {}", clientIp, newCrypto);
        return repository.save(newCrypto);
    }

    /**
     * Fetches a single cryptocurrency from the in-memory H2 database based on the provided ticker in the URL.
     *
     * @param id (String): A String of 3 character that unique defines a currency.
     * @param request (HttpServletRequest): HTTP request containing information about the client.
     * @throws CryptoNotFoundException if the cryptocurrency with the provided ticker does not exist in the database.
     * @return The Crypto object matching the provided ticker.
     */
    @GetMapping("/api/currencies/{id}")
    Crypto getCrypto(@PathVariable String id, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        logger.info("{} requested the crypto currency: {}", clientIp, repository.findById(id));
        return repository.findById(id)
                .orElseThrow(() -> new CryptoNotFoundException(id));
    }

    /**
     * Fetches all cryptocurrencies from the in-memory H2 database and applies paging and sorting based on the URL.
     *
     * @param pageable (Pageable): Pagination and sorting information provided through the URL.
     * @param request (HttpServletRequest): HTTP request containing information about the client.
     * @return A Page<Crypto> containing the cryptocurrencies matching the requested page, size and sorting criteria.
     */
    @GetMapping("/api/currencies")
    Page<Crypto> getAllCrypto(Pageable pageable, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        logger.info("{} requested all crypto currencies", clientIp);
        return repository.findAll(pageable);
    }

    /**
     * Updates the information of a cryptocurrency in the in-memory H2 database based on the new information in the
     * JSON and the provided ticker in the URL. If the ticker is not in the database, it creates a new record with that
     * ticker.
     *
     * @param newCrypto (Crypto): The provided JSON that describes a cryptocurrency is loaded into a custom data type.
     *                  It is used to give updated information for a cryptocurrency.
     * @param id (String): A String of 3 character that unique defines a currency.
     * @param request (HttpServletRequest): HTTP request containing information about the client.
     * @return An updated version of an existing cryptocurrency described as Crypto. Or a new cryptocurrency if the
     * ticker does not exist.
     */
    @PutMapping("/api/currencies/{id}")
    Crypto updateCrypto(@RequestBody Crypto newCrypto, @PathVariable String id, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        return repository.findById(id)
                .map(crypto -> {
                    crypto.setName(newCrypto.getName());
                    crypto.setAmount(newCrypto.getAmount());
                    crypto.setMarketCap(newCrypto.getMarketCap());
                    logger.info("{} updated the crypto currency {}", clientIp, repository.findById(id));
                    return repository.save(crypto);
                })
                .orElseGet(() -> {
                    logger.warn("{} tried to update a crypto currency, but {} got created instead", clientIp, repository.findById(id));
                    return repository.save(newCrypto);
                });
    }

    /**
     * Deletes a cryptocurrency from the in-memory H2 database based on the provided ticker in the URL.
     *
     * @param id (String): A String of 3 character that unique defines a currency.
     * @param request (HttpServletRequest): HTTP request containing information about the client.
     */
    @DeleteMapping("/api/currencies/{id}")
    void deleteCrypto(@PathVariable String id, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        logger.info("{} deleted the crypto currency {}", clientIp, repository.findById(id));
        repository.deleteById(id);
    }
}