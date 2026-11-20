package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.DepartamentoDto;
import com.sis414.parciafinal.entity.Departamento;
import com.sis414.parciafinal.repository.DepartamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<DepartamentoDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public DepartamentoDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public DepartamentoDto create(DepartamentoDto dto) {
        Departamento entity = new Departamento();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public DepartamentoDto update(Long id, DepartamentoDto dto) {
        Departamento entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Departamento getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado: " + id));
    }

    public DepartamentoDto toDto(Departamento e) {
        return new DepartamentoDto(
                e.getId(),
                e.getCodigo(),
                e.getDescripcion(),
                e.getSigla()
        );
    }

    private void apply(DepartamentoDto dto, Departamento e) {
        e.setCodigo(dto.codigo());
        e.setDescripcion(dto.descripcion());
        e.setSigla(dto.sigla());
    }
}
