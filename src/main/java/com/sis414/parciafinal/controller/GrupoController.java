package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.GrupoDto;
import com.sis414.parciafinal.service.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@Tag(name = "Grupos y Auxiliares")
public class GrupoController {

    private final GrupoService service;

    public GrupoController(GrupoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Grupo - Listar")
    public List<GrupoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Grupo - Obtener por id")
    public GrupoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Grupo - Nuevo")
    public GrupoDto create(@RequestBody GrupoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Grupo - Modificar")
    public GrupoDto update(@PathVariable Long id, @RequestBody GrupoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Grupo - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
