package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.OficinaDetalleDto;
import com.sis414.parciafinal.dto.ResponsableFilaDto;
import com.sis414.parciafinal.entity.Oficina;
import com.sis414.parciafinal.entity.Responsable;
import com.sis414.parciafinal.repository.OficinaRepository;
import com.sis414.parciafinal.repository.ResponsableRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Pantalla OFICINA y RESPONSABLE: detalle y botones Activar / Inactivar (api_estado: 1 = ACTIVO, 0 = INACTIVO). */
@RestController
@RequestMapping("/api")
@Tag(name = "Oficinas y Responsables")
public class OficinaPantallaController {

    private final OficinaRepository oficinaRepository;
    private final ResponsableRepository responsableRepository;

    public OficinaPantallaController(OficinaRepository oficinaRepository, ResponsableRepository responsableRepository) {
        this.oficinaRepository = oficinaRepository;
        this.responsableRepository = responsableRepository;
    }

    @GetMapping("/oficinas/{entidad}/{unidad}/{codOfic}/detalle")
    @Operation(summary = "Pantalla OFICINA (oficina, estado y grilla de responsables)")
    public OficinaDetalleDto detalle(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad,
                                     @PathVariable("codOfic") Integer codOfic) {
        Oficina o = oficina(entidad, unidad, codOfic);
        return new OficinaDetalleDto(o.getEntidad(), o.getUnidad(), o.getCodOfic(), o.getNomOfic(),
                estado(o.getApiEstado()), o.getObserv(), filas(entidad, unidad, codOfic));
    }

    @GetMapping("/oficinas/{entidad}/{unidad}/{codOfic}/responsables")
    @Operation(summary = "Grilla Cod. / Responsable / Cargo / CI / Estado")
    public List<ResponsableFilaDto> responsables(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad,
                                                 @PathVariable("codOfic") Integer codOfic) {
        oficina(entidad, unidad, codOfic);
        return filas(entidad, unidad, codOfic);
    }

    @PatchMapping("/oficinas/{entidad}/{unidad}/{codOfic}/activar")
    @Operation(summary = "Oficina - Activar")
    public OficinaDetalleDto activar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad,
                                     @PathVariable("codOfic") Integer codOfic) {
        return cambiar(entidad, unidad, codOfic, 1);
    }

    @PatchMapping("/oficinas/{entidad}/{unidad}/{codOfic}/inactivar")
    @Operation(summary = "Oficina - Inactivar")
    public OficinaDetalleDto inactivar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad,
                                       @PathVariable("codOfic") Integer codOfic) {
        return cambiar(entidad, unidad, codOfic, 0);
    }

    @PatchMapping("/responsables/{entidad}/{unidad}/{codOfic}/{codResp}/activar")
    @Operation(summary = "Responsable - Activar")
    public ResponsableFilaDto activarResponsable(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad,
                                                 @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp) {
        return cambiarResponsable(entidad, unidad, codOfic, codResp, 1);
    }

    @PatchMapping("/responsables/{entidad}/{unidad}/{codOfic}/{codResp}/inactivar")
    @Operation(summary = "Responsable - Inactivar")
    public ResponsableFilaDto inactivarResponsable(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad,
                                                   @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp) {
        return cambiarResponsable(entidad, unidad, codOfic, codResp, 0);
    }

    private OficinaDetalleDto cambiar(String entidad, String unidad, Integer codOfic, int estado) {
        Oficina o = oficina(entidad, unidad, codOfic);
        o.setApiEstado(estado);
        oficinaRepository.save(o);
        return detalle(entidad, unidad, codOfic);
    }

    private ResponsableFilaDto cambiarResponsable(String entidad, String unidad, Integer codOfic, Integer codResp, int estado) {
        Responsable r = responsableRepository.findById(new Responsable.Key(entidad, unidad, codOfic, codResp))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado"));
        r.setApiEstado(estado);
        return fila(responsableRepository.save(r));
    }

    private Oficina oficina(String entidad, String unidad, Integer codOfic) {
        return oficinaRepository.findById(new Oficina.Key(entidad, unidad, codOfic))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oficina no encontrada"));
    }

    private List<ResponsableFilaDto> filas(String entidad, String unidad, Integer codOfic) {
        return responsableRepository.findByEntidadAndUnidadAndCodOfic(entidad, unidad, codOfic).stream().map(this::fila).toList();
    }

    private ResponsableFilaDto fila(Responsable r) {
        return new ResponsableFilaDto(r.getCodResp(), r.getNomResp(), r.getCargo(), r.getCi(), estado(r.getApiEstado()));
    }

    private static String estado(Integer e) {
        return Integer.valueOf(1).equals(e) ? "ACTIVO" : "INACTIVO";
    }
}
