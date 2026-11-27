package com.sis414.parciafinal.dto;

/** Respuesta del login (tabla PASSW, sin la contraseña). */
public record LoginResponse(String usuario, String completo, String grupo, Boolean tipo) {}
