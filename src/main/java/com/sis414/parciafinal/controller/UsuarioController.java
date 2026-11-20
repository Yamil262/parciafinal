package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.UsuarioDto;
import com.sis414.parciafinal.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Administradores")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Usuario - Listar")
    public List<UsuarioDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Usuario - Obtener por id")
    public UsuarioDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Usuario - Nuevo")
    public UsuarioDto create(@RequestBody UsuarioDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Usuario - Modificar")
    public UsuarioDto update(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Usuario - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
