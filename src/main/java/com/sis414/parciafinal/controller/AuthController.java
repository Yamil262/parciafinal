package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.LoginRequest;
import com.sis414.parciafinal.dto.LoginResponse;
import com.sis414.parciafinal.entity.Usuario;
import com.sis414.parciafinal.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @Operation(summary = "Iniciar sesión (tabla PASSW: usuario + psw, es sensible al contexto)")
    public LoginResponse login(@RequestBody LoginRequest req) {
        if (req == null || req.usuario() == null || req.psw() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos");
        }
        Usuario u = usuarioRepository.findById(req.usuario())
                .filter(x -> x.getPsw() != null && x.getPsw().equals(req.psw()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos"));
        return new LoginResponse(u.getUsuario(), u.getCompleto(), u.getGrupo(), u.getTipo());
    }
}
