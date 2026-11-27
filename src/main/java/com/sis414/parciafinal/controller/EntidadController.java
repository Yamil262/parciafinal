package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Entidad;
import com.sis414.parciafinal.repository.EntidadRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Entidad: tabla ENTIDADES.DBF. La clave es la del DBF (gestion, entidad), no hay "id" artificial. */
@RestController
@RequestMapping("/api/entidades")
@Tag(name = "Administrar Unidad")
public class EntidadController {

    private final EntidadRepository repository;

    public EntidadController(EntidadRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Entidad - Listar")
    public List<Entidad> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{gestion}/{entidad}")
    @Operation(summary = "Entidad - Obtener por clave")
    public Entidad findByKey(@PathVariable("gestion") Integer gestion, @PathVariable("entidad") String entidad) {
        return repository.findById(new Entidad.Key(gestion, entidad))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidad no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Entidad - Nuevo")
    public Entidad create(@RequestBody Entidad body) {
        if (body.getGestion() == null || body.getEntidad() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: gestion, entidad");
        }
        if (repository.existsById(new Entidad.Key(body.getGestion(), body.getEntidad()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Entidad ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{gestion}/{entidad}")
    @Operation(summary = "Entidad - Modificar")
    public Entidad update(@PathVariable("gestion") Integer gestion, @PathVariable("entidad") String entidad, @RequestBody Entidad body) {
        if (!repository.existsById(new Entidad.Key(gestion, entidad))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidad no encontrado");
        }
        body.setGestion(gestion);
        body.setEntidad(entidad);
        return repository.save(body);
    }

    @DeleteMapping("/{gestion}/{entidad}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Entidad - Eliminar")
    public void delete(@PathVariable("gestion") Integer gestion, @PathVariable("entidad") String entidad) {
        if (!repository.existsById(new Entidad.Key(gestion, entidad))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidad no encontrado");
        }
        repository.deleteById(new Entidad.Key(gestion, entidad));
    }
}
