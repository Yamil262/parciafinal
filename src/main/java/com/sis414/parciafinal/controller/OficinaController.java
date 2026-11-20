package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.OficinaDto;
import com.sis414.parciafinal.service.OficinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oficinas")
@Tag(name = "Oficinas y Responsables")
public class OficinaController {

    private final OficinaService service;

    public OficinaController(OficinaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Oficina - Listar")
    public List<OficinaDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Oficina - Obtener por id")
    public OficinaDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Oficina - Nuevo")
    public OficinaDto create(@RequestBody OficinaDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Oficina - Modificar")
    public OficinaDto update(@PathVariable Long id, @RequestBody OficinaDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Oficina - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
