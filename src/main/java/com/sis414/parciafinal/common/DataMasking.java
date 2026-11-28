package com.sis414.parciafinal.common;

/** Utilidades para ocultar datos sensibles en las respuestas. */
public final class DataMasking {

    private DataMasking() {
    }

    /** Deja visibles solo los ultimos 3 caracteres: 12345678 -> *****678. */
    public static String mask(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        String v = value.trim();
        if (v.length() <= 3) {
            return "*".repeat(v.length());
        }
        return "*".repeat(v.length() - 3) + v.substring(v.length() - 3);
    }
}
