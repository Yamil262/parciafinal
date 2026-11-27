package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Responsable;
import com.sis414.parciafinal.repository.ResponsableRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Responsable: tabla RESP.DBF. La clave es la del DBF (entidad, unidad, codOfic, codResp), no hay "id" artificial. */
@RestController
@RequestMapping("/api/responsables")
@Tag(name = "Oficinas y Responsables")
public class ResponsableController {

    private final ResponsableRepository repository;

    public ResponsableController(ResponsableRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Responsable - Listar")
    public List<Responsable> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{entidad}/{unidad}/{codOfic}/{codResp}")
    @Operation(summary = "Responsable - Obtener por clave")
    public Responsable findByKey(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp) {
        return repository.findById(new Responsable.Key(entidad, unidad, codOfic, codResp))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Responsable - Nuevo")
    public Responsable create(@RequestBody Responsable body) {
        if (body.getEntidad() == null || body.getUnidad() == null || body.getCodOfic() == null || body.getCodResp() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: entidad, unidad, codOfic, codResp");
        }
        if (repository.existsById(new Responsable.Key(body.getEntidad(), body.getUnidad(), body.getCodOfic(), body.getCodResp()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Responsable ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{entidad}/{unidad}/{codOfic}/{codResp}")
    @Operation(summary = "Responsable - Modificar")
    public Responsable update(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp, @RequestBody Responsable body) {
        if (!repository.existsById(new Responsable.Key(entidad, unidad, codOfic, codResp))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado");
        }
        body.setEntidad(entidad);
        body.setUnidad(unidad);
        body.setCodOfic(codOfic);
        body.setCodResp(codResp);
        return repository.save(body);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codOfic}/{codResp}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Responsable - Eliminar")
    public void delete(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp) {
        if (!repository.existsById(new Responsable.Key(entidad, unidad, codOfic, codResp))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado");
        }
        repository.deleteById(new Responsable.Key(entidad, unidad, codOfic, codResp));
    }
}
