package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Config;
import com.sis414.parciafinal.repository.ConfigRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Configuración: tabla CONFIG.DBF. La clave es la del DBF (entidad, unidad), no hay "id" artificial. */
@RestController
@RequestMapping("/api/configuraciones")
@Tag(name = "Localización BD")
public class ConfigController {

    private final ConfigRepository repository;

    public ConfigController(ConfigRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Configuración - Listar")
    public List<Config> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}")
    @Operation(summary = "Configuración - Obtener por clave")
    public Config findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        return repository.findById(new Config.Key(entidad, unidad))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuración no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Configuración - Nuevo")
    public Config create(@RequestBody Config body) {
        if (body.getEntidad() == null || body.getUnidad() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad");
        }
        if (repository.existsById(new Config.Key(body.getEntidad(), body.getUnidad()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Configuración ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}")
    @Operation(summary = "Configuración - Modificar")
    public Config update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @RequestBody Config body) {
        if (!repository.existsById(new Config.Key(entidad, unidad))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuración no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Configuración - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        if (!repository.existsById(new Config.Key(entidad, unidad))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuración no encontrado");
        }
        repository.deleteById(new Config.Key(entidad, unidad));
    }
}
