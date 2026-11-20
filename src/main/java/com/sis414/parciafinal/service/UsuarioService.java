package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.UsuarioDto;
import com.sis414.parciafinal.entity.Usuario;
import com.sis414.parciafinal.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public UsuarioDto create(UsuarioDto dto) {
        Usuario entity = new Usuario();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public UsuarioDto update(Long id, UsuarioDto dto) {
        Usuario entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Usuario getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id));
    }

    public UsuarioDto toDto(Usuario e) {
        return new UsuarioDto(
                e.getId(),
                e.getUsuario(),
                e.getCompleto(),
                e.getDes(),
                e.getPsw(),
                e.getTipo(),
                e.getGrupo(),
                e.getFeult(),
                e.getUsuar()
        );
    }

    private void apply(UsuarioDto dto, Usuario e) {
        e.setUsuario(dto.usuario());
        e.setCompleto(dto.completo());
        e.setDes(dto.des());
        e.setPsw(dto.psw());
        e.setTipo(dto.tipo());
        e.setGrupo(dto.grupo());
        e.setFeult(dto.feult());
        e.setUsuar(dto.usuar());
    }
}
