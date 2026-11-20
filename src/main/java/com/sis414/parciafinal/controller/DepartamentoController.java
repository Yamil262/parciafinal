package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.DepartamentoDto;
import com.sis414.parciafinal.service.DepartamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@Tag(name = "Oficinas y Responsables")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Departamento - Listar")
    public List<DepartamentoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Departamento - Obtener por id")
    public DepartamentoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Departamento - Nuevo")
    public DepartamentoDto create(@RequestBody DepartamentoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Departamento - Modificar")
    public DepartamentoDto update(@PathVariable Long id, @RequestBody DepartamentoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Departamento - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
