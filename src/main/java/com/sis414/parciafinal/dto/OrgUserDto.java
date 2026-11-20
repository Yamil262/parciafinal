package com.sis414.parciafinal.dto;

import java.time.LocalDate;
public record OrgUserDto(
    Long id,
    String type,
    String idUser,
    String name,
    Boolean readonly,
    Integer ckval,
    String data,
    LocalDate updated
) {}
