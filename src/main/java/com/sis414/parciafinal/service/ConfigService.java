package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.ConfigDto;
import com.sis414.parciafinal.entity.Config;
import com.sis414.parciafinal.repository.ConfigRepository;
import com.sis414.parciafinal.repository.UnidadAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConfigService {

    private final ConfigRepository repository;
    private final UnidadAdminRepository unidadAdminRepository;

    public ConfigService(ConfigRepository repository, UnidadAdminRepository unidadAdminRepository) {
        this.repository = repository;
        this.unidadAdminRepository = unidadAdminRepository;
    }

    @Transactional(readOnly = true)
    public List<ConfigDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ConfigDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public ConfigDto create(ConfigDto dto) {
        Config entity = new Config();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public ConfigDto update(Long id, ConfigDto dto) {
        Config entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Config getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Config no encontrado: " + id));
    }

    public ConfigDto toDto(Config e) {
        return new ConfigDto(
                e.getId(),
                e.getDescEnt(),
                e.getDescUni(),
                e.getUsuario(),
                e.getUnidad() == null ? null : e.getUnidad().getId()
        );
    }

    private void apply(ConfigDto dto, Config e) {
        e.setDescEnt(dto.descEnt());
        e.setDescUni(dto.descUni());
        e.setUsuario(dto.usuario());
        e.setUnidad(dto.unidadId() == null ? null : unidadAdminRepository.getReferenceById(dto.unidadId()));
    }
}
