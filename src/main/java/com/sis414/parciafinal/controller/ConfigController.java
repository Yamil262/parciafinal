package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.dto.*;
import com.sis414.parciafinal.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/configuracion")
@Tag(name = "Configuración", description = "Configuración de entidad/unidad por usuario (tabla CONFIG). Id autogenerado (la tabla original no tiene clave única).")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping
    @Operation(summary = "Listar configuraciones (paginado)")
    public PageResponse<ConfigResponse> listar(@ParameterObject Pageable pageable) {
        return configService.listar(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener configuración por clave")
    public ConfigResponse obtener(@PathVariable("id") Long id) {
        return configService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "Crear configuración")
    public ResponseEntity<ConfigResponse> crear(@Valid @RequestBody ConfigRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(configService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar configuración")
    public ConfigResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody ConfigRequest request) {
        return configService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar configuración")
    public void eliminar(@PathVariable("id") Long id) {
        configService.eliminar(id);
    }
}
