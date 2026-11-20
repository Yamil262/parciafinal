package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.OficinaDetalleDto;
import com.sis414.parciafinal.dto.ResponsableFilaDto;
import com.sis414.parciafinal.service.OficinaResponsablePantallaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oficinas")
@Tag(name = "Oficinas y Responsables")
public class OficinaPantallaController {

    private final OficinaResponsablePantallaService service;

    public OficinaPantallaController(OficinaResponsablePantallaService service) {
        this.service = service;
    }

    @GetMapping("/{id}/detalle")
    @Operation(summary = "Pantalla OFICINA (oficina, estado y grilla de responsables)")
    public OficinaDetalleDto detalle(@PathVariable Long id) {
        return service.detalle(id);
    }

    @GetMapping("/{id}/responsables")
    @Operation(summary = "Grilla Cod. / Responsable / Cargo / CI / Expedido / Estado")
    public List<ResponsableFilaDto> responsables(@PathVariable Long id) {
        return service.responsables(id);
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Oficina - Activar")
    public OficinaDetalleDto activar(@PathVariable Long id) {
        return service.cambiarEstadoOficina(id, 1);
    }

    @PatchMapping("/{id}/inactivar")
    @Operation(summary = "Oficina - Inactivar")
    public OficinaDetalleDto inactivar(@PathVariable Long id) {
        return service.cambiarEstadoOficina(id, 0);
    }
}
