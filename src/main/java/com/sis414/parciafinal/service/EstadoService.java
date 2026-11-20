package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.EstadoDto;
import com.sis414.parciafinal.entity.Estado;
import com.sis414.parciafinal.repository.EstadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EstadoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public EstadoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public EstadoDto create(EstadoDto dto) {
        Estado entity = new Estado();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public EstadoDto update(Long id, EstadoDto dto) {
        Estado entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Estado getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado: " + id));
    }

    public EstadoDto toDto(Estado e) {
        return new EstadoDto(
                e.getId(),
                e.getCodEstado(),
                e.getNomEstado()
        );
    }

    private void apply(EstadoDto dto, Estado e) {
        e.setCodEstado(dto.codEstado());
        e.setNomEstado(dto.nomEstado());
    }
}
