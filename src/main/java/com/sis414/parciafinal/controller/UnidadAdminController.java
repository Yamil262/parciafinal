package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.UnidadAdmin;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Unidad administrativa: tabla UNIDADADMIN.DBF. La clave es la del DBF (entidad, unidad), no hay "id" artificial. */
@RestController
@RequestMapping("/api/unidades-admin")
@Tag(name = "Administrar Unidad")
public class UnidadAdminController {

    private final UnidadAdminRepository repository;

    public UnidadAdminController(UnidadAdminRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Unidad administrativa - Listar")
    public List<UnidadAdmin> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}")
    @Operation(summary = "Unidad administrativa - Obtener por clave")
    public UnidadAdmin findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        return repository.findById(new UnidadAdmin.Key(entidad, unidad))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad administrativa no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Unidad administrativa - Nuevo")
    public UnidadAdmin create(@RequestBody UnidadAdmin body) {
        if (body.getEntidad() == null || body.getUnidad() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad");
        }
        if (repository.existsById(new UnidadAdmin.Key(body.getEntidad(), body.getUnidad()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Unidad administrativa ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}")
    @Operation(summary = "Unidad administrativa - Modificar")
    public UnidadAdmin update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @RequestBody UnidadAdmin body) {
        if (!repository.existsById(new UnidadAdmin.Key(entidad, unidad))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad administrativa no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Unidad administrativa - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        if (!repository.existsById(new UnidadAdmin.Key(entidad, unidad))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad administrativa no encontrado");
        }
        repository.deleteById(new UnidadAdmin.Key(entidad, unidad));
    }
}
