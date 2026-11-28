package com.sis414.parciafinal.service;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.common.PasswordHasher;
import com.sis414.parciafinal.dto.UsuarioRequest;
import com.sis414.parciafinal.dto.UsuarioResponse;
import com.sis414.parciafinal.entity.Usuario;
import com.sis414.parciafinal.exception.BadRequestException;
import com.sis414.parciafinal.exception.ConflictException;
import com.sis414.parciafinal.exception.NotFoundException;
import com.sis414.parciafinal.repository.UsuarioRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public PageResponse<UsuarioResponse> listar(Pageable pageable) {
        return PageResponse.of(repository.findAll(pageable).map(this::toResponse));
    }

    @Transactional(readOnly = true)
    public UsuarioResponse obtener(String usuario) {
        return toResponse(buscar(usuario));
    }

    @Transactional
    public UsuarioResponse crear(UsuarioRequest request) {
        if (request.usuario() == null || request.usuario().isBlank()) {
            throw new BadRequestException("El campo 'usuario' es obligatorio para crear usuario");
        }
        if (request.password() == null || request.password().isBlank()) {
            throw new BadRequestException("El campo 'password' es obligatorio para crear usuario");
        }
        if (repository.existsById(request.usuario())) {
            throw new ConflictException("Ya existe usuario con clave [" + request.usuario() + "]");
        }
        Usuario u = new Usuario();
        u.setUsuario(request.usuario());
        aplicar(u, request);
        return toResponse(repository.save(u));
    }

    @Transactional
    public UsuarioResponse actualizar(String usuario, UsuarioRequest request) {
        Usuario u = buscar(usuario);
        aplicar(u, request);
        return toResponse(repository.save(u));
    }

    @Transactional
    public void eliminar(String usuario) {
        repository.delete(buscar(usuario));
    }

    private Usuario buscar(String usuario) {
        return repository.findById(usuario)
                .orElseThrow(() -> new NotFoundException("No se encontró usuario con clave [" + usuario + "]"));
    }

    private void aplicar(Usuario u, UsuarioRequest r) {
        u.setCompleto(r.completo());
        u.setDes(r.des());
        u.setTipo(r.tipo());
        u.setGrupo(r.grupo());
        if (r.password() != null && !r.password().isBlank()) {
            u.setPsw(PasswordHasher.hash(r.password()));
        }
        u.setFeult(LocalDate.now());
        u.setUsuar((r.usuar() == null || r.usuar().isBlank()) ? "api" : r.usuar());
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(u.getUsuario(), u.getCompleto(), u.getDes(), u.getTipo(), u.getGrupo(),
                u.getFeult(), u.getUsuar());
    }
}
