package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.LoginRequest;
import com.sis414.parciafinal.dto.UsuarioDto;
import com.sis414.parciafinal.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Iniciar sesión")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public UsuarioDto login(@RequestBody LoginRequest req) {
        var u = usuarioRepository.findByUsuario(req.usuario())
                .filter(x -> x.getPsw() != null && x.getPsw().equals(req.psw()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos"));
        return new UsuarioDto(u.getId(), u.getUsuario(), u.getCompleto(), u.getDes(), null, u.getTipo(), u.getGrupo(), u.getFeult(), u.getUsuar());
    }
}
