package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Transferencia;
import com.sis414.parciafinal.repository.TransferenciaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Transferencia: tabla TRASFE.DBF. La clave es la del DBF (entidad, unidad, codigo), no hay "id" artificial. */
@RestController
@RequestMapping("/api/transferencias")
@Tag(name = "Activos Fijos")
public class TransferenciaController {

    private final TransferenciaRepository repository;

    public TransferenciaController(TransferenciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Transferencia - Listar")
    public List<Transferencia> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Transferencia - Obtener por clave")
    public Transferencia findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return repository.findById(new Transferencia.Key(entidad, unidad, codigo))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Transferencia - Nuevo")
    public Transferencia create(@RequestBody Transferencia body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codigo");
        }
        if (repository.existsById(new Transferencia.Key(body.getEntidad(), body.getUnidad(), body.getCodigo()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Transferencia ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Transferencia - Modificar")
    public Transferencia update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo, @RequestBody Transferencia body) {
        if (!repository.existsById(new Transferencia.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodigo(codigo);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Transferencia - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        if (!repository.existsById(new Transferencia.Key(entidad, unidad, codigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia no encontrado");
        }
        repository.deleteById(new Transferencia.Key(entidad, unidad, codigo));
    }
}
