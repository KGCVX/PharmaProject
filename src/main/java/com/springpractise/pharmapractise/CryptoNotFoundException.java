package com.springpractise.pharmapractise;

class CryptoNotFoundException extends RuntimeException {

    CryptoNotFoundException(String id) {
        super("Could not find employee " + id);
    }
}