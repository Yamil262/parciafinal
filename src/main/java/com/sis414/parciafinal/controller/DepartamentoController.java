package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Departamento;
import com.sis414.parciafinal.repository.DepartamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Departamento: tabla CLA_DEPTS.DBF. La clave es la del DBF (codigo), no hay "id" artificial. */
@RestController
@RequestMapping("/api/departamentos")
@Tag(name = "Oficinas y Responsables")
public class DepartamentoController {

    private final DepartamentoRepository repository;

    public DepartamentoController(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Departamento - Listar")
    public List<Departamento> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Departamento - Obtener por clave")
    public Departamento findByKey(@PathVariable("codigo") Integer codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Departamento - Nuevo")
    public Departamento create(@RequestBody Departamento body) {
        if (body.getCodigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: codigo");
        }
        if (repository.existsById(body.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Departamento ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Departamento - Modificar")
    public Departamento update(@PathVariable("codigo") Integer codigo, @RequestBody Departamento body) {
        if (!repository.existsById(codigo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado");
        }
        body.setCodigo(codigo);
        return repository.save(body);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Departamento - Eliminar")
    public void delete(@PathVariable("codigo") Integer codigo) {
        if (!repository.existsById(codigo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado");
        }
        repository.deleteById(codigo);
    }
}
