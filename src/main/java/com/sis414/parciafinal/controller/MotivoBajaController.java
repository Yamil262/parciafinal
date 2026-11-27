package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.MotivoBaja;
import com.sis414.parciafinal.repository.MotivoBajaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Motivo de baja: tabla BAJA.DBF. La clave es la del DBF (codBaja), no hay "id" artificial. */
@RestController
@RequestMapping("/api/motivos-baja")
@Tag(name = "Activos Fijos")
public class MotivoBajaController {

    private final MotivoBajaRepository repository;

    public MotivoBajaController(MotivoBajaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Motivo de baja - Listar")
    public List<MotivoBaja> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{codBaja}")
    @Operation(summary = "Motivo de baja - Obtener por clave")
    public MotivoBaja findByKey(@PathVariable("codBaja") Integer codBaja) {
        return repository.findById(codBaja)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motivo de baja no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Motivo de baja - Nuevo")
    public MotivoBaja create(@RequestBody MotivoBaja body) {
        if (body.getCodBaja() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: codBaja");
        }
        if (repository.existsById(body.getCodBaja())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Motivo de baja ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{codBaja}")
    @Operation(summary = "Motivo de baja - Modificar")
    public MotivoBaja update(@PathVariable("codBaja") Integer codBaja, @RequestBody MotivoBaja body) {
        if (!repository.existsById(codBaja)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Motivo de baja no encontrado");
        }
        body.setCodBaja(codBaja);
        return repository.save(body);
    }

    @DeleteMapping("/{codBaja}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Motivo de baja - Eliminar")
    public void delete(@PathVariable("codBaja") Integer codBaja) {
        if (!repository.existsById(codBaja)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Motivo de baja no encontrado");
        }
        repository.deleteById(codBaja);
    }
}
