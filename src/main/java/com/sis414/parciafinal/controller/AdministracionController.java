package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.GestionDto;
import com.sis414.parciafinal.entity.Usuario;
import com.sis414.parciafinal.repository.UsuarioRepository;
import com.sis414.parciafinal.service.ConfiguracionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/administracion")
@Tag(name = "Administradores")
public class AdministracionController {

    private final ConfiguracionService configuracion;
    private final UsuarioRepository usuarioRepository;

    public AdministracionController(ConfiguracionService configuracion, UsuarioRepository usuarioRepository) {
        this.configuracion = configuracion;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/gestion")
    @Operation(summary = "Gestion actual (CIERRE DE GESTION)")
    public GestionDto gestion() {
        return new GestionDto(configuracion.gestion());
    }

    @PostMapping("/cambiar-gestion")
    @Operation(summary = "Cambiar Gestion")
    public GestionDto cambiarGestion(@RequestBody GestionDto req) {
        if (req.gestion() == null || req.gestion() < 1900) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gestion invalida");
        }
        configuracion.set(ConfiguracionService.GESTION, String.valueOf(req.gestion()));
        return new GestionDto(req.gestion());
    }

    @PostMapping("/cerrar-gestion")
    @Operation(summary = "Cerrar Gestion (pendiente)")
    public void cerrarGestion() {
        pendiente("Cerrar Gestion");
    }

    @PostMapping("/importar")
    @Operation(summary = "Importar (pendiente)")
    public void importar() {
        pendiente("Importar");
    }

    @PostMapping("/exportar")
    @Operation(summary = "Exportar (pendiente)")
    public void exportar() {
        pendiente("Exportar");
    }

    @GetMapping("/indices-ufv")
    @Operation(summary = "Indices UFV (pendiente: no hay tabla de UFV entre las 24)")
    public void indicesUfv() {
        pendiente("Indices UFV");
    }

    @GetMapping("/seguridad")
    @Operation(summary = "Seguridad (usuarios del sistema)")
    public List<Usuario> seguridad() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/reindexar")
    @Operation(summary = "Re-indexar")
    public Map<String, String> reindexar() {
        return Map.of("mensaje", "No aplica: los indices los administra la base de datos (JPA).");
    }

    @PostMapping("/migrador")
    @Operation(summary = "Migrador (pendiente)")
    public void migrador() {
        pendiente("Migrador");
    }

    private static void pendiente(String accion) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, accion + ": aun no implementado");
    }
}
