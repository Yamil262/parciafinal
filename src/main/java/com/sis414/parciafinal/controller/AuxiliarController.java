package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.AuxiliarDto;
import com.sis414.parciafinal.service.AuxiliarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auxiliares")
@Tag(name = "Grupos y Auxiliares")
public class AuxiliarController {

    private final AuxiliarService service;

    public AuxiliarController(AuxiliarService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Auxiliar - Listar")
    public List<AuxiliarDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Auxiliar - Obtener por id")
    public AuxiliarDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Auxiliar - Nuevo")
    public AuxiliarDto create(@RequestBody AuxiliarDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Auxiliar - Modificar")
    public AuxiliarDto update(@PathVariable Long id, @RequestBody AuxiliarDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Auxiliar - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
