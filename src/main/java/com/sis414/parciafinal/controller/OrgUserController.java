package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.OrgUserDto;
import com.sis414.parciafinal.service.OrgUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/org-user")
@Tag(name = "Administradores")
public class OrgUserController {

    private final OrgUserService service;

    public OrgUserController(OrgUserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "OrgUser - Listar")
    public List<OrgUserDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "OrgUser - Obtener por id")
    public OrgUserDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "OrgUser - Nuevo")
    public OrgUserDto create(@RequestBody OrgUserDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "OrgUser - Modificar")
    public OrgUserDto update(@PathVariable Long id, @RequestBody OrgUserDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "OrgUser - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
