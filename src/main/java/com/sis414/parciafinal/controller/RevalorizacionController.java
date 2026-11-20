package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.RevalorizacionDto;
import com.sis414.parciafinal.service.RevalorizacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revalorizaciones")
@Tag(name = "Activos Fijos")
public class RevalorizacionController {

    private final RevalorizacionService service;

    public RevalorizacionController(RevalorizacionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Revalorizacion - Listar")
    public List<RevalorizacionDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Revalorizacion - Obtener por id")
    public RevalorizacionDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Revalorizacion - Nuevo")
    public RevalorizacionDto create(@RequestBody RevalorizacionDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Revalorizacion - Modificar")
    public RevalorizacionDto update(@PathVariable Long id, @RequestBody RevalorizacionDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Revalorizacion - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
