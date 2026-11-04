package com.springpractise.pharmapractise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CryptoRepository repository) {

        return args -> {
            repository.save(new Crypto("BTC", "Bitcoin", 16770000L, 189580000000L));
            repository.save(new Crypto("ETH", "Ethereum", 96710000L, 69280000000L));
            repository.save(new Crypto("XRP", "Ripple", 38590000000L, 64750000000L));
            repository.save(new Crypto("BCH", "BitcoinCash", 16670000L, 69020000000L));
        };
    }
}
