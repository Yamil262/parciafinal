package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.ObjetoGasto;
import com.sis414.parciafinal.repository.ObjetoGastoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Objeto de gasto: tabla OBJGASTO.DBF. La clave es la del DBF (gestion, partida), no hay "id" artificial. */
@RestController
@RequestMapping("/api/objetos-gasto")
@Tag(name = "Grupos y Auxiliares")
public class ObjetoGastoController {

    private final ObjetoGastoRepository repository;

    public ObjetoGastoController(ObjetoGastoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Objeto de gasto - Listar")
    public List<ObjetoGasto> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{gestion}/{partida}")
    @Operation(summary = "Objeto de gasto - Obtener por clave")
    public ObjetoGasto findByKey(@PathVariable("gestion") Integer gestion, @PathVariable("partida") String partida) {
        return repository.findById(new ObjetoGasto.Key(gestion, partida))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto de gasto no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Objeto de gasto - Nuevo")
    public ObjetoGasto create(@RequestBody ObjetoGasto body) {
        if (body.getGestion() == null || body.getPartida() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: gestion, partida");
        }
        if (repository.existsById(new ObjetoGasto.Key(body.getGestion(), body.getPartida()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Objeto de gasto ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{gestion}/{partida}")
    @Operation(summary = "Objeto de gasto - Modificar")
    public ObjetoGasto update(@PathVariable("gestion") Integer gestion, @PathVariable("partida") String partida, @RequestBody ObjetoGasto body) {
        if (!repository.existsById(new ObjetoGasto.Key(gestion, partida))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto de gasto no encontrado");
        }
        body.setGestion(gestion);
        body.setPartida(partida);
        return repository.save(body);
    }

    @DeleteMapping("/{gestion}/{partida}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Objeto de gasto - Eliminar")
    public void delete(@PathVariable("gestion") Integer gestion, @PathVariable("partida") String partida) {
        if (!repository.existsById(new ObjetoGasto.Key(gestion, partida))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto de gasto no encontrado");
        }
        repository.deleteById(new ObjetoGasto.Key(gestion, partida));
    }
}
