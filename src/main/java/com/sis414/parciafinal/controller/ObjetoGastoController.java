package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ObjetoGastoDto;
import com.sis414.parciafinal.service.ObjetoGastoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetos-gasto")
@Tag(name = "Grupos y Auxiliares")
public class ObjetoGastoController {

    private final ObjetoGastoService service;

    public ObjetoGastoController(ObjetoGastoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "ObjetoGasto - Listar")
    public List<ObjetoGastoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "ObjetoGasto - Obtener por id")
    public ObjetoGastoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "ObjetoGasto - Nuevo")
    public ObjetoGastoDto create(@RequestBody ObjetoGastoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ObjetoGasto - Modificar")
    public ObjetoGastoDto update(@PathVariable Long id, @RequestBody ObjetoGastoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "ObjetoGasto - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
