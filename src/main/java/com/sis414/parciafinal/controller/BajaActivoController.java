package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.BajaActivo;
import com.sis414.parciafinal.repository.BajaActivoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Baja de activo: tabla BAJAS.DBF. La clave es la del DBF (entidad, unidad, codigo), no hay "id" artificial. */
@RestController
@RequestMapping("/api/bajas")
@Tag(name = "Activos Fijos")
public class BajaActivoController {

    private final BajaActivoRepository repository;

    public BajaActivoController(BajaActivoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Baja de activo - Listar")
    public List<BajaActivo> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Baja de activo - Obtener por clave")
    public BajaActivo findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return repository.findById(new BajaActivo.Key(entidad, unidad, codigo))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Baja de activo no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Baja de activo - Nuevo")
    public BajaActivo create(@RequestBody BajaActivo body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codigo");
        }
        if (repository.existsById(new BajaActivo.Key(body.getEntidad(), body.getUnidad(), body.getCodigo()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Baja de activo ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Baja de activo - Modificar")
    public BajaActivo update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo, @RequestBody BajaActivo body) {
        if (!repository.existsById(new BajaActivo.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Baja de activo no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodigo(codigo);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Baja de activo - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        if (!repository.existsById(new BajaActivo.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Baja de activo no encontrado");
        }
        repository.deleteById(new BajaActivo.Key(entidad, unidad, codigo));
    }
}
