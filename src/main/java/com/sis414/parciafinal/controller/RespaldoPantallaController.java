package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.RespaldarRequest;
import com.sis414.parciafinal.dto.RespaldoDto;
import com.sis414.parciafinal.service.RespaldoPantallaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/respaldos")
@Tag(name = "Respaldos BD")
public class RespaldoPantallaController {

    private final RespaldoPantallaService service;

    public RespaldoPantallaController(RespaldoPantallaService service) {
        this.service = service;
    }

    @PostMapping("/respaldar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Respaldar",
            description = "Registra el respaldo (archivo .zip, dia, hora, usuario, descripcion). Aun no genera el archivo fisico.")
    public RespaldoDto respaldar(@RequestBody RespaldarRequest req) {
        return service.respaldar(req);
    }

    @PostMapping("/{id}/restaurar")
    @Operation(summary = "Restaurar (pendiente)")
    public void restaurar(@PathVariable Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Restaurar: aun no implementado");
    }

    @PostMapping("/{id}/copiar-a")
    @Operation(summary = "Copiar a ... (pendiente)")
    public void copiarA(@PathVariable Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Copiar a: aun no implementado");
    }

    @PostMapping("/copiar-de")
    @Operation(summary = "Copiar de ... (pendiente)")
    public void copiarDe() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Copiar de: aun no implementado");
    }
}
