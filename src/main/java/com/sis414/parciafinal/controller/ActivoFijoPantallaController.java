package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ActivoFijoDto;
import com.sis414.parciafinal.dto.ActivoFijoFichaDto;
import com.sis414.parciafinal.dto.DuplicarRequest;
import com.sis414.parciafinal.dto.FechaCalculoDto;
import com.sis414.parciafinal.service.ActivoFijoPantallaService;
import com.sis414.parciafinal.service.CalculoActivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/activos-fijos")
@Tag(name = "Activos Fijos")
public class ActivoFijoPantallaController {

    private final ActivoFijoPantallaService service;
    private final CalculoActivoService calculoService;

    public ActivoFijoPantallaController(ActivoFijoPantallaService service, CalculoActivoService calculoService) {
        this.service = service;
        this.calculoService = calculoService;
    }

    @GetMapping("/{id}/ficha")
    @Operation(summary = "Pantalla ACTIVOS FIJOS (ficha completa con CALCULADOS A)",
            description = "Sin dia/mes/ano usa la fecha de hoy. ufvIncorporacion = INDICE UFV; ufvCalculo = UFV de CALCULADOS A.")
    public ActivoFijoFichaDto ficha(@PathVariable Long id,
                                    @RequestParam(required = false) Integer dia,
                                    @RequestParam(required = false) Integer mes,
                                    @RequestParam(required = false) Integer ano,
                                    @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                    @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.ficha(id, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/hoy")
    @Operation(summary = "Hoy (fecha de CALCULADOS A)")
    public FechaCalculoDto hoy() {
        return calculoService.hoy();
    }

    @GetMapping("/primero")
    @Operation(summary = "Primero")
    public ActivoFijoFichaDto primero(@RequestParam(required = false) Integer dia,
                                      @RequestParam(required = false) Integer mes,
                                      @RequestParam(required = false) Integer ano,
                                      @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                      @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.primero(dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/ultimo")
    @Operation(summary = "Ultimo")
    public ActivoFijoFichaDto ultimo(@RequestParam(required = false) Integer dia,
                                     @RequestParam(required = false) Integer mes,
                                     @RequestParam(required = false) Integer ano,
                                     @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                     @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.ultimo(dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/{id}/siguiente")
    @Operation(summary = "Siguiente")
    public ActivoFijoFichaDto siguiente(@PathVariable Long id,
                                        @RequestParam(required = false) Integer dia,
                                        @RequestParam(required = false) Integer mes,
                                        @RequestParam(required = false) Integer ano,
                                        @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                        @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.siguiente(id, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/{id}/anterior")
    @Operation(summary = "Anterior")
    public ActivoFijoFichaDto anterior(@PathVariable Long id,
                                       @RequestParam(required = false) Integer dia,
                                       @RequestParam(required = false) Integer mes,
                                       @RequestParam(required = false) Integer ano,
                                       @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                       @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.anterior(id, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar")
    public List<ActivoFijoDto> buscar(@RequestParam(required = false) String codigo,
                                      @RequestParam(required = false) String descripcion,
                                      @RequestParam(required = false) Long grupoId,
                                      @RequestParam(required = false) Long oficinaId,
                                      @RequestParam(required = false) Long responsableId) {
        return service.buscar(codigo, descripcion, grupoId, oficinaId, responsableId);
    }

    @PostMapping("/{id}/duplicar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Duplicar",
            description = "Copia el activo en estado ELABORADO. Sin nuevoCodigo genera codigo-2, codigo-3, etc.")
    public ActivoFijoDto duplicar(@PathVariable Long id, @RequestBody(required = false) DuplicarRequest req) {
        return service.duplicar(id, req);
    }
}
