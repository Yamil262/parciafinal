package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.dto.UsuarioRequest;
import com.sis414.parciafinal.dto.UsuarioResponse;
import com.sis414.parciafinal.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Usuarios del sistema (tabla PASSW). Nunca se devuelve la contraseña ni su hash.")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Listar usuarios (paginado)")
    public PageResponse<UsuarioResponse> listar(@ParameterObject Pageable pageable) {
        return usuarioService.listar(pageable);
    }

    @GetMapping("/{usuario}")
    @Operation(summary = "Obtener usuario")
    public UsuarioResponse obtener(@PathVariable("usuario") String usuario) {
        return usuarioService.obtener(usuario);
    }

    @PostMapping
    @Operation(summary = "Crear usuario (la contraseña se guarda con hash PBKDF2)")
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(request));
    }

    @PutMapping("/{usuario}")
    @Operation(summary = "Modificar usuario (password opcional)")
    public UsuarioResponse actualizar(@PathVariable("usuario") String usuario,
                                      @Valid @RequestBody UsuarioRequest request) {
        return usuarioService.actualizar(usuario, request);
    }

    @DeleteMapping("/{usuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar usuario")
    public void eliminar(@PathVariable("usuario") String usuario) {
        usuarioService.eliminar(usuario);
    }
}
