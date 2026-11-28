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
@RequestMapping("/api/meses")
@Tag(name = "Meses", description = "Meses del año (tabla MES).")
public class MesController {

    private final MesService mesService;

    public MesController(MesService mesService) {
        this.mesService = mesService;
    }

    @GetMapping
    @Operation(summary = "Listar meses (paginado)")
    public PageResponse<MesResponse> listar(@ParameterObject Pageable pageable) {
        return mesService.listar(pageable);
    }

    @GetMapping("/{mes}")
    @Operation(summary = "Obtener mes por clave")
    public MesResponse obtener(@PathVariable("mes") Integer mes) {
        return mesService.obtener(mes);
    }

    @PostMapping
    @Operation(summary = "Crear mes")
    public ResponseEntity<MesResponse> crear(@Valid @RequestBody MesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesService.crear(request));
    }

    @PutMapping("/{mes}")
    @Operation(summary = "Modificar mes")
    public MesResponse actualizar(@PathVariable("mes") Integer mes, @Valid @RequestBody MesRequest request) {
        return mesService.actualizar(mes, request);
    }

    @DeleteMapping("/{mes}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar mes")
    public void eliminar(@PathVariable("mes") Integer mes) {
        mesService.eliminar(mes);
    }
}
