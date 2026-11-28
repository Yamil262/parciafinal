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
@RequestMapping("/api/cuentas-contables")
@Tag(name = "Cuentas contables", description = "Clasificador de cuentas contables de activos fijos (tabla CODCONT).")
public class CodContController {

    private final CodContService codContService;
    private final AuxiliarService auxiliarService;

    public CodContController(CodContService codContService, AuxiliarService auxiliarService) {
        this.codContService = codContService;
        this.auxiliarService = auxiliarService;
    }

    @GetMapping
    @Operation(summary = "Listar cuentas contables (paginado)")
    public PageResponse<CodContResponse> listar(@ParameterObject Pageable pageable) {
        return codContService.listar(pageable);
    }

    @GetMapping("/{codCont}")
    @Operation(summary = "Obtener cuenta contable por clave")
    public CodContResponse obtener(@PathVariable("codCont") Integer codCont) {
        return codContService.obtener(codCont);
    }

    @PostMapping
    @Operation(summary = "Crear cuenta contable")
    public ResponseEntity<CodContResponse> crear(@Valid @RequestBody CodContRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(codContService.crear(request));
    }

    @PutMapping("/{codCont}")
    @Operation(summary = "Modificar cuenta contable")
    public CodContResponse actualizar(@PathVariable("codCont") Integer codCont, @Valid @RequestBody CodContRequest request) {
        return codContService.actualizar(codCont, request);
    }

    @DeleteMapping("/{codCont}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar cuenta contable")
    public void eliminar(@PathVariable("codCont") Integer codCont) {
        codContService.eliminar(codCont);
    }

    @GetMapping("/{codCont}/auxiliares")
    @Operation(summary = "Auxiliares de la cuenta")
    public List<AuxiliarResponse> auxiliares(@PathVariable("codCont") Integer codCont) {
        return auxiliarService.listarPorCuenta(codCont);
    }
}
