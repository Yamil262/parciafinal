package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.AuxiliarFilaDto;
import com.sis414.parciafinal.dto.GrupoDetalleDto;
import com.sis414.parciafinal.entity.Grupo;
import com.sis414.parciafinal.repository.AuxiliarRepository;
import com.sis414.parciafinal.repository.GrupoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Pantalla GRUPO CONTABLE. */
@RestController
@RequestMapping("/api/grupos")
@Tag(name = "Grupos y Auxiliares")
public class GrupoPantallaController {

    private final GrupoRepository grupoRepository;
    private final AuxiliarRepository auxiliarRepository;

    public GrupoPantallaController(GrupoRepository grupoRepository, AuxiliarRepository auxiliarRepository) {
        this.grupoRepository = grupoRepository;
        this.auxiliarRepository = auxiliarRepository;
    }

    @GetMapping("/{codCont}/detalle")
    @Operation(summary = "Pantalla GRUPO CONTABLE (grupo y grilla de auxiliares)")
    public GrupoDetalleDto detalle(@PathVariable("codCont") Integer codCont) {
        Grupo g = grupoRepository.findById(codCont)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado: " + codCont));
        return new GrupoDetalleDto(g.getCodCont(), g.getNombre(), g.getVidaUtil(), g.getObserv(),
                g.getDepreciar(), g.getActualizar(), filas(codCont));
    }

    @GetMapping("/{codCont}/auxiliares")
    @Operation(summary = "Auxiliares del grupo")
    public List<AuxiliarFilaDto> auxiliares(@PathVariable("codCont") Integer codCont) {
        return filas(codCont);
    }

    private List<AuxiliarFilaDto> filas(Integer codCont) {
        return auxiliarRepository.findByCodCont(codCont).stream()
                .map(a -> new AuxiliarFilaDto(a.getEntidad(), a.getUnidad(), a.getCodAux(), a.getNomAux()))
                .toList();
    }
}
