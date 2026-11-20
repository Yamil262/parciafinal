package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.TransferenciaDto;
import com.sis414.parciafinal.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
@Tag(name = "Activos Fijos")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Transferencia - Listar")
    public List<TransferenciaDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Transferencia - Obtener por id")
    public TransferenciaDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Transferencia - Nuevo")
    public TransferenciaDto create(@RequestBody TransferenciaDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Transferencia - Modificar")
    public TransferenciaDto update(@PathVariable Long id, @RequestBody TransferenciaDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Transferencia - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
