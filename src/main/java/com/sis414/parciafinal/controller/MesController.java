package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Mes;
import com.sis414.parciafinal.repository.MesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Mes: tabla MES.DBF. La clave es la del DBF (mes), no hay "id" artificial. */
@RestController
@RequestMapping("/api/meses")
@Tag(name = "Activos Fijos")
public class MesController {

    private final MesRepository repository;

    public MesController(MesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Mes - Listar")
    public List<Mes> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{mes}")
    @Operation(summary = "Mes - Obtener por clave")
    public Mes findByKey(@PathVariable("mes") Integer mes) {
        return repository.findById(mes)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mes no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Mes - Nuevo")
    public Mes create(@RequestBody Mes body) {
        if (body.getMes() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: mes");
        }
        if (repository.existsById(body.getMes())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Mes ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{mes}")
    @Operation(summary = "Mes - Modificar")
    public Mes update(@PathVariable("mes") Integer mes, @RequestBody Mes body) {
        if (!repository.existsById(mes)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mes no encontrado");
        }
        body.setMes(mes);
        return repository.save(body);
    }

    @DeleteMapping("/{mes}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Mes - Eliminar")
    public void delete(@PathVariable("mes") Integer mes) {
        if (!repository.existsById(mes)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mes no encontrado");
        }
        repository.deleteById(mes);
    }
}
