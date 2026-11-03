package com.springpractise.pharmapractise;

import org.springframework.data.jpa.repository.JpaRepository;

interface CryptoRepository extends JpaRepository<Crypto, String> {

}