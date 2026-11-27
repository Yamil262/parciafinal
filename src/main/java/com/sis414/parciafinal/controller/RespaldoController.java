package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Respaldo;
import com.sis414.parciafinal.repository.RespaldoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Respaldo: tabla BACKUPS.DBF. La clave es la del DBF (archivo), no hay "id" artificial. */
@RestController
@RequestMapping("/api/respaldos")
@Tag(name = "Respaldos BD")
public class RespaldoController {

    private final RespaldoRepository repository;

    public RespaldoController(RespaldoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Respaldo - Listar")
    public List<Respaldo> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{archivo}")
    @Operation(summary = "Respaldo - Obtener por clave")
    public Respaldo findByKey(@PathVariable("archivo") String archivo) {
        return repository.findById(archivo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Respaldo no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Respaldo - Nuevo")
    public Respaldo create(@RequestBody Respaldo body) {
        if (body.getArchivo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: archivo");
        }
        if (repository.existsById(body.getArchivo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Respaldo ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{archivo}")
    @Operation(summary = "Respaldo - Modificar")
    public Respaldo update(@PathVariable("archivo") String archivo, @RequestBody Respaldo body) {
        if (!repository.existsById(archivo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Respaldo no encontrado");
        }
        body.setArchivo(archivo);
        return repository.save(body);
    }

    @DeleteMapping("/{archivo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Respaldo - Eliminar")
    public void delete(@PathVariable("archivo") String archivo) {
        if (!repository.existsById(archivo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Respaldo no encontrado");
        }
        repository.deleteById(archivo);
    }
}
