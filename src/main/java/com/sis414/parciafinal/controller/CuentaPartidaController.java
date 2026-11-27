package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.CuentaPartida;
import com.sis414.parciafinal.repository.CuentaPartidaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Cuenta-partida: tabla CTA_PAR.DBF. La clave es la del DBF (codCont, partida, gestion), no hay "id" artificial. */
@RestController
@RequestMapping("/api/cuentas-partidas")
@Tag(name = "Grupos y Auxiliares")
public class CuentaPartidaController {

    private final CuentaPartidaRepository repository;

    public CuentaPartidaController(CuentaPartidaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Cuenta-partida - Listar")
    public List<CuentaPartida> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{codCont}/{partida}/{gestion}")
    @Operation(summary = "Cuenta-partida - Obtener por clave")
    public CuentaPartida findByKey(@PathVariable("codCont") Integer codCont, @PathVariable("partida") String partida, @PathVariable("gestion") Integer gestion) {
        return repository.findById(new CuentaPartida.Key(codCont, partida, gestion))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta-partida no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cuenta-partida - Nuevo")
    public CuentaPartida create(@RequestBody CuentaPartida body) {
        if (body.getCodCont() == null || body.getPartida() == null || body.getGestion() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: codCont, partida, gestion");
        }
        if (repository.existsById(new CuentaPartida.Key(body.getCodCont(), body.getPartida(), body.getGestion()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cuenta-partida ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{codCont}/{partida}/{gestion}")
    @Operation(summary = "Cuenta-partida - Modificar")
    public CuentaPartida update(@PathVariable("codCont") Integer codCont, @PathVariable("partida") String partida, @PathVariable("gestion") Integer gestion, @RequestBody CuentaPartida body) {
        if (!repository.existsById(new CuentaPartida.Key(codCont, partida, gestion))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta-partida no encontrado");
        }
        body.setCodCont(codCont);
        body.setPartida(partida);
        body.setGestion(gestion);
        return repository.save(body);
    }

    @DeleteMapping("/{codCont}/{partida}/{gestion}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Cuenta-partida - Eliminar")
    public void delete(@PathVariable("codCont") Integer codCont, @PathVariable("partida") String partida, @PathVariable("gestion") Integer gestion) {
        if (!repository.existsById(new CuentaPartida.Key(codCont, partida, gestion))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta-partida no encontrado");
        }
        repository.deleteById(new CuentaPartida.Key(codCont, partida, gestion));
    }
}
