package com.prueba.franquicia.domain.exceptions;

public class BranchNotFoundException extends Exception {
    public BranchNotFoundException(String message) {
        super(message);
    }
}
