package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Oficina;
import com.sis414.parciafinal.repository.OficinaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Oficina: tabla OFICINA.DBF. La clave es la del DBF (entidad, unidad, codOfic), no hay "id" artificial. */
@RestController
@RequestMapping("/api/oficinas")
@Tag(name = "Oficinas y Responsables")
public class OficinaController {

    private final OficinaRepository repository;

    public OficinaController(OficinaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Oficina - Listar")
    public List<Oficina> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codOfic}")
    @Operation(summary = "Oficina - Obtener por clave")
    public Oficina findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic) {
        return repository.findById(new Oficina.Key(entidad, unidad, codOfic))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Oficina - Nuevo")
    public Oficina create(@RequestBody Oficina body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodOfic() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codOfic");
        }
        if (repository.existsById(new Oficina.Key(body.getEntidad(), body.getUnidad(), body.getCodOfic()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Oficina ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codOfic}")
    @Operation(summary = "Oficina - Modificar")
    public Oficina update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @RequestBody Oficina body) {
        if (!repository.existsById(new Oficina.Key(entidad, unidad, codOfic))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodOfic(codOfic);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codOfic}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Oficina - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic) {
        if (!repository.existsById(new Oficina.Key(entidad, unidad, codOfic))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrado");
        }
        repository.deleteById(new Oficina.Key(entidad, unidad, codOfic));
    }
}
