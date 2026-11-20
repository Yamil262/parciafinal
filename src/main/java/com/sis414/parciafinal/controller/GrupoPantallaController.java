package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.AuxiliarFilaDto;
import com.sis414.parciafinal.dto.GrupoDetalleDto;
import com.sis414.parciafinal.service.GrupoPantallaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@Tag(name = "Grupos y Auxiliares")
public class GrupoPantallaController {

    private final GrupoPantallaService service;

    public GrupoPantallaController(GrupoPantallaService service) {
        this.service = service;
    }

    @GetMapping("/{id}/detalle")
    @Operation(summary = "Pantalla GRUPO CONTABLE (grupo + grilla de auxiliares)")
    public GrupoDetalleDto detalle(@PathVariable Long id) {
        return service.detalle(id);
    }

    @GetMapping("/{id}/auxiliares")
    @Operation(summary = "Grilla CODIGO / NOMBRE AUXILIAR del grupo")
    public List<AuxiliarFilaDto> auxiliares(@PathVariable Long id) {
        return service.auxiliares(id);
    }
}
