package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.OrganismoFin;
import com.sis414.parciafinal.repository.OrganismoFinRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Organismo financiador: tabla ORGANISMO_FIN.DBF. La clave es la del DBF (gestion, codOf), no hay "id" artificial. */
@RestController
@RequestMapping("/api/organismos-financiadores")
@Tag(name = "Activos Fijos")
public class OrganismoFinController {

    private final OrganismoFinRepository repository;

    public OrganismoFinController(OrganismoFinRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Organismo financiador - Listar")
    public List<OrganismoFin> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{gestion}/{codOf}")
    @Operation(summary = "Organismo financiador - Obtener por clave")
    public OrganismoFin findByKey(@PathVariable("gestion") Integer gestion, @PathVariable("codOf") String codOf) {
        return repository.findById(new OrganismoFin.Key(gestion, codOf))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organismo financiador no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Organismo financiador - Nuevo")
    public OrganismoFin create(@RequestBody OrganismoFin body) {
        if (body.getGestion() == null || body.getCodOf() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: gestion, codOf");
        }
        if (repository.existsById(new OrganismoFin.Key(body.getGestion(), body.getCodOf()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Organismo financiador ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{gestion}/{codOf}")
    @Operation(summary = "Organismo financiador - Modificar")
    public OrganismoFin update(@PathVariable("gestion") Integer gestion, @PathVariable("codOf") String codOf, @RequestBody OrganismoFin body) {
        if (!repository.existsById(new OrganismoFin.Key(gestion, codOf))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organismo financiador no encontrado");
        }
        body.setGestion(gestion);
        body.setCodOf(codOf);
        return repository.save(body);
    }

    @DeleteMapping("/{gestion}/{codOf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Organismo financiador - Eliminar")
    public void delete(@PathVariable("gestion") Integer gestion, @PathVariable("codOf") String codOf) {
        if (!repository.existsById(new OrganismoFin.Key(gestion, codOf))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organismo financiador no encontrado");
        }
        repository.deleteById(new OrganismoFin.Key(gestion, codOf));
    }
}
