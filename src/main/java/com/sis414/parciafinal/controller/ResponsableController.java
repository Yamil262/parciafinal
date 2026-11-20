package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ResponsableDto;
import com.sis414.parciafinal.service.ResponsableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsables")
@Tag(name = "Oficinas y Responsables")
public class ResponsableController {

    private final ResponsableService service;

    public ResponsableController(ResponsableService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Responsable - Listar")
    public List<ResponsableDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Responsable - Obtener por id")
    public ResponsableDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Responsable - Nuevo")
    public ResponsableDto create(@RequestBody ResponsableDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Responsable - Modificar")
    public ResponsableDto update(@PathVariable Long id, @RequestBody ResponsableDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Responsable - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
