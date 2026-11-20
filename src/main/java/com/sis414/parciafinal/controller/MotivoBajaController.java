package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.MotivoBajaDto;
import com.sis414.parciafinal.service.MotivoBajaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motivos-baja")
@Tag(name = "Activos Fijos")
public class MotivoBajaController {

    private final MotivoBajaService service;

    public MotivoBajaController(MotivoBajaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "MotivoBaja - Listar")
    public List<MotivoBajaDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "MotivoBaja - Obtener por id")
    public MotivoBajaDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "MotivoBaja - Nuevo")
    public MotivoBajaDto create(@RequestBody MotivoBajaDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "MotivoBaja - Modificar")
    public MotivoBajaDto update(@PathVariable Long id, @RequestBody MotivoBajaDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "MotivoBaja - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
