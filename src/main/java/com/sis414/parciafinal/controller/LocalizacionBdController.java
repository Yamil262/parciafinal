package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.LocalizacionDto;
import com.sis414.parciafinal.service.ConfiguracionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/localizacion-bd")
@Tag(name = "Localización BD")
public class LocalizacionBdController {

    private final ConfiguracionService configuracion;

    public LocalizacionBdController(ConfiguracionService configuracion) {
        this.configuracion = configuracion;
    }

    @GetMapping
    @Operation(summary = "Directorio donde se encuentra la Base de Datos")
    public LocalizacionDto obtener() {
        return new LocalizacionDto(configuracion.get(ConfiguracionService.DIRECTORIO, "c:\\vsiaf\\"));
    }

    @PutMapping
    @Operation(summary = "Grabar")
    public LocalizacionDto grabar(@RequestBody LocalizacionDto req) {
        if (req.directorio() == null || req.directorio().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Directorio requerido");
        }
        configuracion.set(ConfiguracionService.DIRECTORIO, req.directorio().trim());
        return new LocalizacionDto(req.directorio().trim());
    }
}
