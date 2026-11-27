package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Auxiliar;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Auxiliar: tabla AUXILIAR.DBF. La clave es la del DBF (entidad, unidad, codCont, codAux), no hay "id" artificial. */
@RestController
@RequestMapping("/api/auxiliares")
@Tag(name = "Grupos y Auxiliares")
public class AuxiliarController {

    private final AuxiliarRepository repository;

    public AuxiliarController(AuxiliarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Auxiliar - Listar")
    public List<Auxiliar> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codCont}/{codAux}")
    @Operation(summary = "Auxiliar - Obtener por clave")
    public Auxiliar findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codCont") Integer codCont, @PathVariable("codAux") Integer codAux) {
        return repository.findById(new Auxiliar.Key(entidad, unidad, codCont, codAux))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auxiliar no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Auxiliar - Nuevo")
    public Auxiliar create(@RequestBody Auxiliar body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodCont() == null || body.getCodAux() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codCont, codAux");
        }
        if (repository.existsById(new Auxiliar.Key(body.getEntidad(), body.getUnidad(), body.getCodCont(), body.getCodAux()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Auxiliar ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codCont}/{codAux}")
    @Operation(summary = "Auxiliar - Modificar")
    public Auxiliar update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codCont") Integer codCont, @PathVariable("codAux") Integer codAux, @RequestBody Auxiliar body) {
        if (!repository.existsById(new Auxiliar.Key(entidad, unidad, codCont, codAux))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auxiliar no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodCont(codCont);
        body.setCodAux(codAux);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codCont}/{codAux}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Auxiliar - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codCont") Integer codCont, @PathVariable("codAux") Integer codAux) {
        if (!repository.existsById(new Auxiliar.Key(entidad, unidad, codCont, codAux))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auxiliar no encontrado");
        }
        repository.deleteById(new Auxiliar.Key(entidad, unidad, codCont, codAux));
    }
}
