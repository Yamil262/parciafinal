package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ActivoFijoFichaDto;
import com.sis414.parciafinal.dto.DuplicarRequest;
import com.sis414.parciafinal.dto.FechaCalculoDto;
import com.sis414.parciafinal.dto.TransferirRequest;
import com.sis414.parciafinal.entity.ActivoFijo;
import com.sis414.parciafinal.service.ActivoFijoPantallaService;
import com.sis414.parciafinal.service.CalculoActivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/** Botones de la pantalla ADMINISTRACION DE ACTIVOS FIJOS. La clave de un activo es entidad + unidad + codigo. */
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

    @GetMapping("/hoy")
    @Operation(summary = "Hoy (fecha de CALCULADOS A)")
    public FechaCalculoDto hoy() {
        return calculoService.hoy();
    }

    @GetMapping("/primero")
    @Operation(summary = "Primero (por codigo) de la unidad")
    public ActivoFijoFichaDto primero(@RequestParam("entidad") String entidad,
                                      @RequestParam("unidad") String unidad,
                                      @RequestParam(required = false) Integer dia,
                                      @RequestParam(required = false) Integer mes,
                                      @RequestParam(required = false) Integer ano,
                                      @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                      @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.primero(entidad, unidad, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/ultimo")
    @Operation(summary = "Ultimo (por codigo) de la unidad")
    public ActivoFijoFichaDto ultimo(@RequestParam("entidad") String entidad,
                                     @RequestParam("unidad") String unidad,
                                     @RequestParam(required = false) Integer dia,
                                     @RequestParam(required = false) Integer mes,
                                     @RequestParam(required = false) Integer ano,
                                     @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                     @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.ultimo(entidad, unidad, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar (por codigo, descripcion, grupo, oficina o responsable)")
    public List<ActivoFijo> buscar(@RequestParam(required = false) String entidad,
                                   @RequestParam(required = false) String unidad,
                                   @RequestParam(required = false) String codigo,
                                   @RequestParam(required = false) String descripcion,
                                   @RequestParam(required = false) Integer codCont,
                                   @RequestParam(required = false) Integer codOfic,
                                   @RequestParam(required = false) Integer codResp) {
        return service.buscar(entidad, unidad, codigo, descripcion, codCont, codOfic, codResp);
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}/ficha")
    @Operation(summary = "Pantalla ACTIVOS FIJOS (ficha completa con CALCULADOS A)",
            description = "Sin dia/mes/ano usa la fecha de hoy. ufvIncorporacion = INDICE UFV; ufvCalculo = UFV de CALCULADOS A.")
    public ActivoFijoFichaDto ficha(@PathVariable("entidad") String entidad,
                                    @PathVariable("unidad") String unidad,
                                    @PathVariable("codigo") String codigo,
                                    @RequestParam(required = false) Integer dia,
                                    @RequestParam(required = false) Integer mes,
                                    @RequestParam(required = false) Integer ano,
                                    @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                    @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.ficha(entidad, unidad, codigo, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}/siguiente")
    @Operation(summary = "Siguiente")
    public ActivoFijoFichaDto siguiente(@PathVariable("entidad") String entidad,
                                        @PathVariable("unidad") String unidad,
                                        @PathVariable("codigo") String codigo,
                                        @RequestParam(required = false) Integer dia,
                                        @RequestParam(required = false) Integer mes,
                                        @RequestParam(required = false) Integer ano,
                                        @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                        @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.siguiente(entidad, unidad, codigo, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}/anterior")
    @Operation(summary = "Anterior")
    public ActivoFijoFichaDto anterior(@PathVariable("entidad") String entidad,
                                       @PathVariable("unidad") String unidad,
                                       @PathVariable("codigo") String codigo,
                                       @RequestParam(required = false) Integer dia,
                                       @RequestParam(required = false) Integer mes,
                                       @RequestParam(required = false) Integer ano,
                                       @RequestParam(required = false) BigDecimal ufvIncorporacion,
                                       @RequestParam(required = false) BigDecimal ufvCalculo) {
        return service.anterior(entidad, unidad, codigo, dia, mes, ano, ufvIncorporacion, ufvCalculo);
    }

    @PostMapping("/{entidad}/{unidad}/{codigo}/duplicar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Duplicar",
            description = "Copia el activo en estado ELABORADO. Sin nuevoCodigo genera codigo-2, codigo-3, etc.")
    public ActivoFijo duplicar(@PathVariable("entidad") String entidad,
                               @PathVariable("unidad") String unidad,
                               @PathVariable("codigo") String codigo,
                               @RequestBody(required = false) DuplicarRequest req) {
        return service.duplicar(entidad, unidad, codigo, req);
    }

    @PostMapping("/{entidad}/{unidad}/{codigo}/transferir")
    @Operation(summary = "Transferir (nueva oficina y responsable; registra la fila en TRASFE)")
    public ActivoFijo transferir(@PathVariable("entidad") String entidad,
                                 @PathVariable("unidad") String unidad,
                                 @PathVariable("codigo") String codigo,
                                 @RequestBody TransferirRequest req) {
        return service.transferir(entidad, unidad, codigo, req);
    }

    @PatchMapping("/{entidad}/{unidad}/{codigo}/aprobar")
    @Operation(summary = "Aprobar (API_ESTADO = 1)")
    public ActivoFijo aprobar(@PathVariable("entidad") String entidad,
                              @PathVariable("unidad") String unidad,
                              @PathVariable("codigo") String codigo) {
        return service.aprobar(entidad, unidad, codigo);
    }
}
