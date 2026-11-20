package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.MesDto;
import com.sis414.parciafinal.service.MesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meses")
@Tag(name = "Activos Fijos")
public class MesController {

    private final MesService service;

    public MesController(MesService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Mes - Listar")
    public List<MesDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mes - Obtener por id")
    public MesDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Mes - Nuevo")
    public MesDto create(@RequestBody MesDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mes - Modificar")
    public MesDto update(@PathVariable Long id, @RequestBody MesDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Mes - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
