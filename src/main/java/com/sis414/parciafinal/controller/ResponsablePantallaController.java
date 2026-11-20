package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ResponsableFilaDto;
import com.sis414.parciafinal.service.OficinaResponsablePantallaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/responsables")
@Tag(name = "Oficinas y Responsables")
public class ResponsablePantallaController {

    private final OficinaResponsablePantallaService service;

    public ResponsablePantallaController(OficinaResponsablePantallaService service) {
        this.service = service;
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Responsable - Activar")
    public ResponsableFilaDto activar(@PathVariable Long id) {
        return service.cambiarEstadoResponsable(id, 1);
    }

    @PatchMapping("/{id}/inactivar")
    @Operation(summary = "Responsable - Inactivar")
    public ResponsableFilaDto inactivar(@PathVariable Long id) {
        return service.cambiarEstadoResponsable(id, 0);
    }
}
