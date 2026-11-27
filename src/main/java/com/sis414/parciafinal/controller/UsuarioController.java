package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Usuario;
import com.sis414.parciafinal.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Usuario: tabla PASSW.DBF. La clave es la del DBF (usuario), no hay "id" artificial. */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Administradores")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Usuario - Listar")
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{usuario}")
    @Operation(summary = "Usuario - Obtener por clave")
    public Usuario findByKey(@PathVariable("usuario") String usuario) {
        return repository.findById(usuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Usuario - Nuevo")
    public Usuario create(@RequestBody Usuario body) {
        if (body.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: usuario");
        }
        if (repository.existsById(body.getUsuario())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{usuario}")
    @Operation(summary = "Usuario - Modificar")
    public Usuario update(@PathVariable("usuario") String usuario, @RequestBody Usuario body) {
        if (!repository.existsById(usuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        body.setUsuario(usuario);
        return repository.save(body);
    }

    @DeleteMapping("/{usuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Usuario - Eliminar")
    public void delete(@PathVariable("usuario") String usuario) {
        if (!repository.existsById(usuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        repository.deleteById(usuario);
    }
}
