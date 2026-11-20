package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ActivoFijoDto;
import com.sis414.parciafinal.service.ActivoFijoService;
import com.sis414.parciafinal.dto.TransferirRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activos-fijos")
@Tag(name = "Activos Fijos")
public class ActivoFijoController {

    private final ActivoFijoService service;

    public ActivoFijoController(ActivoFijoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "ActivoFijo - Listar")
    public List<ActivoFijoDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "ActivoFijo - Obtener por id")
    public ActivoFijoDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "ActivoFijo - Nuevo")
    public ActivoFijoDto create(@RequestBody ActivoFijoDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ActivoFijo - Modificar")
    public ActivoFijoDto update(@PathVariable Long id, @RequestBody ActivoFijoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "ActivoFijo - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/transferir")
    @Operation(summary = "Transferir activo a otra oficina/responsable")
    public ActivoFijoDto transferir(@PathVariable Long id, @RequestBody TransferirRequest req) {
        return service.transferir(id, req);
    }

    @PatchMapping("/{id}/aprobar")
    @Operation(summary = "Aprobar activo (ELABORADO a APROBADO)")
    public ActivoFijoDto aprobar(@PathVariable Long id) {
        return service.aprobar(id);
    }
}
