package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Revalorizacion;
import com.sis414.parciafinal.repository.RevalorizacionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Revalorización: tabla REVAL.DBF. La clave es la del DBF (entidad, unidad, codigo), no hay "id" artificial. */
@RestController
@RequestMapping("/api/revalorizaciones")
@Tag(name = "Activos Fijos")
public class RevalorizacionController {

    private final RevalorizacionRepository repository;

    public RevalorizacionController(RevalorizacionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Revalorización - Listar")
    public List<Revalorizacion> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Revalorización - Obtener por clave")
    public Revalorizacion findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return repository.findById(new Revalorizacion.Key(entidad, unidad, codigo))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Revalorización no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Revalorización - Nuevo")
    public Revalorizacion create(@RequestBody Revalorizacion body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codigo");
        }
        if (repository.existsById(new Revalorizacion.Key(body.getEntidad(), body.getUnidad(), body.getCodigo()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Revalorización ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Revalorización - Modificar")
    public Revalorizacion update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo, @RequestBody Revalorizacion body) {
        if (!repository.existsById(new Revalorizacion.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revalorización no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodigo(codigo);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Revalorización - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        if (!repository.existsById(new Revalorizacion.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revalorización no encontrado");
        }
        repository.deleteById(new Revalorizacion.Key(entidad, unidad, codigo));
    }
}
