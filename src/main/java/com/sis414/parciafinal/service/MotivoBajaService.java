package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.MotivoBajaDto;
import com.sis414.parciafinal.entity.MotivoBaja;
import com.sis414.parciafinal.repository.MotivoBajaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MotivoBajaService {

    private final MotivoBajaRepository repository;

    public MotivoBajaService(MotivoBajaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<MotivoBajaDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public MotivoBajaDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public MotivoBajaDto create(MotivoBajaDto dto) {
        MotivoBaja entity = new MotivoBaja();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public MotivoBajaDto update(Long id, MotivoBajaDto dto) {
        MotivoBaja entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public MotivoBaja getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoBaja no encontrado: " + id));
    }

    public MotivoBajaDto toDto(MotivoBaja e) {
        return new MotivoBajaDto(
                e.getId(),
                e.getCodBaja(),
                e.getDescBaja()
        );
    }

    private void apply(MotivoBajaDto dto, MotivoBaja e) {
        e.setCodBaja(dto.codBaja());
        e.setDescBaja(dto.descBaja());
    }
}
