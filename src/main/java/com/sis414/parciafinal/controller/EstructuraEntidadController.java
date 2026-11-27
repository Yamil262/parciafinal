package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.EstructuraEntidad;
import com.sis414.parciafinal.repository.EstructuraEntidadRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Estructura de entidad: tabla EST_ENT.DBF. La clave es la del DBF (gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst), no hay "id" artificial. */
@RestController
@RequestMapping("/api/estructuras-entidad")
@Tag(name = "Administrar Unidad")
public class EstructuraEntidadController {

    private final EstructuraEntidadRepository repository;

    public EstructuraEntidadController(EstructuraEntidadRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Estructura de entidad - Listar")
    public List<EstructuraEntidad> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{gestion}/{sectorEnt}/{subsecEnt}/{areaEnt}/{subareaEnt}/{nivelInst}")
    @Operation(summary = "Estructura de entidad - Obtener por clave")
    public EstructuraEntidad findByKey(@PathVariable("gestion") Integer gestion, @PathVariable("sectorEnt") Integer sectorEnt, @PathVariable("subsecEnt") Integer subsecEnt, @PathVariable("areaEnt") Integer areaEnt, @PathVariable("subareaEnt") Integer subareaEnt, @PathVariable("nivelInst") Integer nivelInst) {
        return repository.findById(new EstructuraEntidad.Key(gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estructura de entidad no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Estructura de entidad - Nuevo")
    public EstructuraEntidad create(@RequestBody EstructuraEntidad body) {
        if (body.getGestion() == null || body.getSectorEnt() == null || body.getSubsecEnt() == null || body.getAreaEnt() == null || body.getSubareaEnt() == null || body.getNivelInst() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst");
        }
        if (repository.existsById(new EstructuraEntidad.Key(body.getGestion(), body.getSectorEnt(), body.getSubsecEnt(), body.getAreaEnt(), body.getSubareaEnt(), body.getNivelInst()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Estructura de entidad ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{gestion}/{sectorEnt}/{subsecEnt}/{areaEnt}/{subareaEnt}/{nivelInst}")
    @Operation(summary = "Estructura de entidad - Modificar")
    public EstructuraEntidad update(@PathVariable("gestion") Integer gestion, @PathVariable("sectorEnt") Integer sectorEnt, @PathVariable("subsecEnt") Integer subsecEnt, @PathVariable("areaEnt") Integer areaEnt, @PathVariable("subareaEnt") Integer subareaEnt, @PathVariable("nivelInst") Integer nivelInst, @RequestBody EstructuraEntidad body) {
        if (!repository.existsById(new EstructuraEntidad.Key(gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estructura de entidad no encontrado");
        }
        body.setGestion(gestion);
        body.setSectorEnt(sectorEnt);
        body.setSubsecEnt(subsecEnt);
        body.setAreaEnt(areaEnt);
        body.setSubareaEnt(subareaEnt);
        body.setNivelInst(nivelInst);
        return repository.save(body);
    }

    @DeleteMapping("/{gestion}/{sectorEnt}/{subsecEnt}/{areaEnt}/{subareaEnt}/{nivelInst}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Estructura de entidad - Eliminar")
    public void delete(@PathVariable("gestion") Integer gestion, @PathVariable("sectorEnt") Integer sectorEnt, @PathVariable("subsecEnt") Integer subsecEnt, @PathVariable("areaEnt") Integer areaEnt, @PathVariable("subareaEnt") Integer subareaEnt, @PathVariable("nivelInst") Integer nivelInst) {
        if (!repository.existsById(new EstructuraEntidad.Key(gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estructura de entidad no encontrado");
        }
        repository.deleteById(new EstructuraEntidad.Key(gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst));
    }
}
