package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Estado;
import com.sis414.parciafinal.repository.EstadoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Estado: tabla ESTADO.DBF. La clave es la del DBF (codEstado), no hay "id" artificial. */
@RestController
@RequestMapping("/api/estados")
@Tag(name = "Activos Fijos")
public class EstadoController {

    private final EstadoRepository repository;

    public EstadoController(EstadoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Estado - Listar")
    public List<Estado> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{codEstado}")
    @Operation(summary = "Estado - Obtener por clave")
    public Estado findByKey(@PathVariable("codEstado") Integer codEstado) {
        return repository.findById(codEstado)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Estado - Nuevo")
    public Estado create(@RequestBody Estado body) {
        if (body.getCodEstado() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: codEstado");
        }
        if (repository.existsById(body.getCodEstado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Estado ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{codEstado}")
    @Operation(summary = "Estado - Modificar")
    public Estado update(@PathVariable("codEstado") Integer codEstado, @RequestBody Estado body) {
        if (!repository.existsById(codEstado)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado");
        }
        body.setCodEstado(codEstado);
        return repository.save(body);
    }

    @DeleteMapping("/{codEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Estado - Eliminar")
    public void delete(@PathVariable("codEstado") Integer codEstado) {
        if (!repository.existsById(codEstado)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado");
        }
        repository.deleteById(codEstado);
    }
}
