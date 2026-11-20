package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.BajaActivoDto;
import com.sis414.parciafinal.service.BajaActivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bajas")
@Tag(name = "Activos Fijos")
public class BajaActivoController {

    private final BajaActivoService service;

    public BajaActivoController(BajaActivoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "BajaActivo - Listar")
    public List<BajaActivoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "BajaActivo - Obtener por id")
    public BajaActivoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "BajaActivo - Nuevo")
    public BajaActivoDto create(@RequestBody BajaActivoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "BajaActivo - Modificar")
    public BajaActivoDto update(@PathVariable Long id, @RequestBody BajaActivoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "BajaActivo - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
