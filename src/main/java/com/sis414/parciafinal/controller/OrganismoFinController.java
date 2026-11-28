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
@RequestMapping("/api/organismos-financiadores")
@Tag(name = "Organismos financiadores", description = "Organismos financiadores por gestión (tabla ORGANISMO_FIN).")
public class OrganismoFinController {

    private final OrganismoFinService organismoFinService;

    public OrganismoFinController(OrganismoFinService organismoFinService) {
        this.organismoFinService = organismoFinService;
    }

    @GetMapping
    @Operation(summary = "Listar organismos financiadores (paginado)")
    public PageResponse<OrganismoFinResponse> listar(@ParameterObject Pageable pageable) {
        return organismoFinService.listar(pageable);
    }

    @GetMapping("/{gestion}/{codOf}")
    @Operation(summary = "Obtener organismo financiador por clave")
    public OrganismoFinResponse obtener(@PathVariable("gestion") Integer gestion, @PathVariable("codOf") String codOf) {
        return organismoFinService.obtener(gestion, codOf);
    }

    @PostMapping
    @Operation(summary = "Crear organismo financiador")
    public ResponseEntity<OrganismoFinResponse> crear(@Valid @RequestBody OrganismoFinRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organismoFinService.crear(request));
    }

    @PutMapping("/{gestion}/{codOf}")
    @Operation(summary = "Modificar organismo financiador")
    public OrganismoFinResponse actualizar(@PathVariable("gestion") Integer gestion, @PathVariable("codOf") String codOf, @Valid @RequestBody OrganismoFinRequest request) {
        return organismoFinService.actualizar(gestion, codOf, request);
    }

    @DeleteMapping("/{gestion}/{codOf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar organismo financiador")
    public void eliminar(@PathVariable("gestion") Integer gestion, @PathVariable("codOf") String codOf) {
        organismoFinService.eliminar(gestion, codOf);
    }
}
