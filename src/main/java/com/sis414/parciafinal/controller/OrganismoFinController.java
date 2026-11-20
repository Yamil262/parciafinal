package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.OrganismoFinDto;
import com.sis414.parciafinal.service.OrganismoFinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organismos-financiadores")
@Tag(name = "Activos Fijos")
public class OrganismoFinController {

    private final OrganismoFinService service;

    public OrganismoFinController(OrganismoFinService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "OrganismoFin - Listar")
    public List<OrganismoFinDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "OrganismoFin - Obtener por id")
    public OrganismoFinDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "OrganismoFin - Nuevo")
    public OrganismoFinDto create(@RequestBody OrganismoFinDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "OrganismoFin - Modificar")
    public OrganismoFinDto update(@PathVariable Long id, @RequestBody OrganismoFinDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "OrganismoFin - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
