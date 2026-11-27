package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.repository.GrupoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Grupo contable: tabla CODCONT.DBF. La clave es la del DBF (codCont), no hay "id" artificial. */
@RestController
@RequestMapping("/api/grupos")
@Tag(name = "Grupos y Auxiliares")
public class GrupoController {

    private final GrupoRepository repository;

    public GrupoController(GrupoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Grupo contable - Listar")
    public List<Grupo> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{codCont}")
    @Operation(summary = "Grupo contable - Obtener por clave")
    public Grupo findByKey(@PathVariable("codCont") Integer codCont) {
        return repository.findById(codCont)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo contable no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Grupo contable - Nuevo")
    public Grupo create(@RequestBody Grupo body) {
        if (body.getCodCont() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: codCont");
        }
        if (repository.existsById(body.getCodCont())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Grupo contable ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{codCont}")
    @Operation(summary = "Grupo contable - Modificar")
    public Grupo update(@PathVariable("codCont") Integer codCont, @RequestBody Grupo body) {
        if (!repository.existsById(codCont)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo contable no encontrado");
        }
        body.setCodCont(codCont);
        return repository.save(body);
    }

    @DeleteMapping("/{codCont}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Grupo contable - Eliminar")
    public void delete(@PathVariable("codCont") Integer codCont) {
        if (!repository.existsById(codCont)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo contable no encontrado");
        }
        repository.deleteById(codCont);
    }
}
