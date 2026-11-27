package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.RespaldarRequest;
import com.sis414.parciafinal.entity.Respaldo;
import com.sis414.parciafinal.repository.RespaldoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Pantalla ADMINISTRACION DE BACKUPS. Solo registra el respaldo en BACKUPS (archivo, dia, hora, usuario, descripcion). */
@RestController
@RequestMapping("/api/respaldos")
@Tag(name = "Respaldos BD")
public class RespaldoPantallaController {

    private final RespaldoRepository repository;

    public RespaldoPantallaController(RespaldoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/respaldar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Respaldar",
            description = "Registra el respaldo (archivo, dia, hora, usuario, descripcion). Aun no genera el archivo fisico.")
    public Respaldo respaldar(@RequestBody RespaldarRequest req) {
        LocalDateTime now = LocalDateTime.now();
        Respaldo r = new Respaldo();
        r.setArchivo(now.format(DateTimeFormatter.ofPattern("yyMMddHHmmss")));
        r.setDia(now.toLocalDate());
        r.setHora(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        r.setObserv(req == null ? null : req.descripcion());
        String usuario = req == null ? null : req.usuario();
        r.setUsuar(usuario == null ? null : (usuario.length() > 8 ? usuario.substring(0, 8) : usuario));
        r.setFeult(now.toLocalDate());
        return repository.save(r);
    }

    @PostMapping("/{archivo}/restaurar")
    @Operation(summary = "Restaurar (pendiente)")
    public void restaurar(@PathVariable("archivo") String archivo) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Restaurar: aun no implementado");
    }

    @PostMapping("/{archivo}/copiar-a")
    @Operation(summary = "Copiar a ... (pendiente)")
    public void copiarA(@PathVariable("archivo") String archivo) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Copiar a: aun no implementado");
    }

    @PostMapping("/copiar-de")
    @Operation(summary = "Copiar de ... (pendiente)")
    public void copiarDe() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Copiar de: aun no implementado");
    }
}
