package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.repository.ActivoFijoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Activo fijo: tabla ACTUAL.DBF. La clave es la del DBF (entidad, unidad, codigo), no hay "id" artificial. */
@RestController
@RequestMapping("/api/activos-fijos")
@Tag(name = "Activos Fijos")
public class ActivoFijoController {

    private final ActivoFijoRepository repository;

    public ActivoFijoController(ActivoFijoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Activo fijo - Listar")
    public List<ActivoFijo> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Activo fijo - Obtener por clave")
    public ActivoFijo findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return repository.findById(new ActivoFijo.Key(entidad, unidad, codigo))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activo fijo no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Activo fijo - Nuevo")
    public ActivoFijo create(@RequestBody ActivoFijo body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codigo");
        }
        if (repository.existsById(new ActivoFijo.Key(body.getEntidad(), body.getUnidad(), body.getCodigo()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Activo fijo ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Activo fijo - Modificar")
    public ActivoFijo update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo, @RequestBody ActivoFijo body) {
        if (!repository.existsById(new ActivoFijo.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activo fijo no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodigo(codigo);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Activo fijo - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        if (!repository.existsById(new ActivoFijo.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activo fijo no encontrado");
        }
        repository.deleteById(new ActivoFijo.Key(entidad, unidad, codigo));
    }
}
