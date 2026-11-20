package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ConfigDto;
import com.sis414.parciafinal.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuraciones")
@Tag(name = "Localización BD")
public class ConfigController {

    private final ConfigService service;

    public ConfigController(ConfigService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Config - Listar")
    public List<ConfigDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Config - Obtener por id")
    public ConfigDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Config - Nuevo")
    public ConfigDto create(@RequestBody ConfigDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Config - Modificar")
    public ConfigDto update(@PathVariable Long id, @RequestBody ConfigDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Config - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
