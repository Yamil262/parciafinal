package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.UnidadSeleccionadaDto;
import com.sis414.parciafinal.entity.UnidadAdmin;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import com.sis414.parciafinal.service.ConfiguracionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/** Pantalla ADMINISTRACION UNIDAD ADMINISTRATIVA: botón Seleccionar. */
@RestController
@RequestMapping("/api/unidades-admin")
@Tag(name = "Administrar Unidad")
public class UnidadAdminPantallaController {

    private final UnidadAdminRepository repository;
    private final ConfiguracionService configuracion;

    public UnidadAdminPantallaController(UnidadAdminRepository repository, ConfiguracionService configuracion) {
        this.repository = repository;
        this.configuracion = configuracion;
    }

    @PostMapping("/{entidad}/{unidad}/seleccionar")
    @Operation(summary = "Seleccionar unidad (queda en la cabecera ENTIDAD / UNIDAD)")
    public UnidadSeleccionadaDto seleccionar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        UnidadAdmin u = repository.findById(new UnidadAdmin.Key(entidad, unidad))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad no encontrada"));
        configuracion.seleccionarUnidad(entidad, unidad);
        return toDto(u);
    }

    @GetMapping("/seleccionada")
    @Operation(summary = "Unidad seleccionada actualmente")
    public UnidadSeleccionadaDto seleccionada() {
        String[] sel = configuracion.unidadSeleccionada();
        if (sel == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay unidad seleccionada");
        return repository.findById(new UnidadAdmin.Key(sel[0], sel[1])).map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay unidad seleccionada"));
    }

    private UnidadSeleccionadaDto toDto(UnidadAdmin u) {
        return new UnidadSeleccionadaDto(u.getEntidad(), u.getUnidad(), u.getDescrip(), u.getCiudad());
    }
}
