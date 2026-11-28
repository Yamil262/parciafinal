package com.sis414.parciafinal.exception;

/** Dato invalido o referencia inexistente (400). */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
