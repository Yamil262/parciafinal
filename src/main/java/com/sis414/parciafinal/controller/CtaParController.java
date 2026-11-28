package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.dto.*;
import com.sis414.parciafinal.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas-partidas")
@Tag(name = "Cuentas y partidas", description = "Relación entre cuenta contable y partida presupuestaria por gestión (tabla CTA_PAR).")
public class CtaParController {

    private final CtaParService ctaParService;

    public CtaParController(CtaParService ctaParService) {
        this.ctaParService = ctaParService;
    }

    @GetMapping
    @Operation(summary = "Listar relaciones cuenta-partida (paginado)")
    public PageResponse<CtaParResponse> listar(@ParameterObject Pageable pageable) {
        return ctaParService.listar(pageable);
    }

    @GetMapping("/{codCont}/{partida}/{gestion}")
    @Operation(summary = "Obtener relación cuenta-partida por clave")
    public CtaParResponse obtener(@PathVariable("codCont") Integer codCont, @PathVariable("partida") String partida, @PathVariable("gestion") Integer gestion) {
        return ctaParService.obtener(codCont, partida, gestion);
    }

    @PostMapping
    @Operation(summary = "Crear relación cuenta-partida")
    public ResponseEntity<CtaParResponse> crear(@Valid @RequestBody CtaParRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ctaParService.crear(request));
    }

    @PutMapping("/{codCont}/{partida}/{gestion}")
    @Operation(summary = "Modificar relación cuenta-partida")
    public CtaParResponse actualizar(@PathVariable("codCont") Integer codCont, @PathVariable("partida") String partida, @PathVariable("gestion") Integer gestion, @Valid @RequestBody CtaParRequest request) {
        return ctaParService.actualizar(codCont, partida, gestion, request);
    }

    @DeleteMapping("/{codCont}/{partida}/{gestion}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar relación cuenta-partida")
    public void eliminar(@PathVariable("codCont") Integer codCont, @PathVariable("partida") String partida, @PathVariable("gestion") Integer gestion) {
        ctaParService.eliminar(codCont, partida, gestion);
    }
}
