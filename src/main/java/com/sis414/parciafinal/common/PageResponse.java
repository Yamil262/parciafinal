package com.sis414.parciafinal.common;

import org.springframework.data.domain.Page;

import java.util.List;

/** Respuesta paginada estable (evita serializar PageImpl directamente). */
public record PageResponse<T>(List<T> content, int page, int size, long totalElements, int totalPages) {

    public static <T> PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }
}
