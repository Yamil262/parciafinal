package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.CuentaPartidaDto;
import com.sis414.parciafinal.service.CuentaPartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas-partidas")
@Tag(name = "Grupos y Auxiliares")
public class CuentaPartidaController {

    private final CuentaPartidaService service;

    public CuentaPartidaController(CuentaPartidaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "CuentaPartida - Listar")
    public List<CuentaPartidaDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "CuentaPartida - Obtener por id")
    public CuentaPartidaDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "CuentaPartida - Nuevo")
    public CuentaPartidaDto create(@RequestBody CuentaPartidaDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "CuentaPartida - Modificar")
    public CuentaPartidaDto update(@PathVariable Long id, @RequestBody CuentaPartidaDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "CuentaPartida - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
