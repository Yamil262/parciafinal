package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.EstadoDto;
import com.sis414.parciafinal.service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
@Tag(name = "Activos Fijos")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Estado - Listar")
    public List<EstadoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Estado - Obtener por id")
    public EstadoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Estado - Nuevo")
    public EstadoDto create(@RequestBody EstadoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Estado - Modificar")
    public EstadoDto update(@PathVariable Long id, @RequestBody EstadoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Estado - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
