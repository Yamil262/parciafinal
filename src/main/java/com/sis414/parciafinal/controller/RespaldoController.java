package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.RespaldoDto;
import com.sis414.parciafinal.service.RespaldoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respaldos")
@Tag(name = "Respaldos BD")
public class RespaldoController {

    private final RespaldoService service;

    public RespaldoController(RespaldoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Respaldo - Listar")
    public List<RespaldoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Respaldo - Obtener por id")
    public RespaldoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Respaldo - Nuevo")
    public RespaldoDto create(@RequestBody RespaldoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Respaldo - Modificar")
    public RespaldoDto update(@PathVariable Long id, @RequestBody RespaldoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Respaldo - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
