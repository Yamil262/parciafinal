package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.EntidadDto;
import com.sis414.parciafinal.entity.Entidad;
import com.sis414.parciafinal.repository.EntidadRepository;
import com.sis414.parciafinal.repository.EstructuraEntidadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntidadService {

    private final EntidadRepository repository;
    private final EstructuraEntidadRepository estructuraEntidadRepository;

    public EntidadService(EntidadRepository repository, EstructuraEntidadRepository estructuraEntidadRepository) {
        this.repository = repository;
        this.estructuraEntidadRepository = estructuraEntidadRepository;
    }

    @Transactional(readOnly = true)
    public List<EntidadDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public EntidadDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public EntidadDto create(EntidadDto dto) {
        Entidad entity = new Entidad();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public EntidadDto update(Long id, EntidadDto dto) {
        Entidad entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public Entidad getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidad no encontrado: " + id));
    }

    public EntidadDto toDto(Entidad e) {
        return new EntidadDto(
                e.getId(),
                e.getGestion(),
                e.getCodigo(),
                e.getDescEnt(),
                e.getSiglaEnt(),
                e.getSectorEnt(),
                e.getSubsecEnt(),
                e.getAreaEnt(),
                e.getSubareaEnt(),
                e.getNivelInst(),
                e.getEstructura() == null ? null : e.getEstructura().getId()
        );
    }

    private void apply(EntidadDto dto, Entidad e) {
        e.setGestion(dto.gestion());
        e.setCodigo(dto.codigo());
        e.setDescEnt(dto.descEnt());
        e.setSiglaEnt(dto.siglaEnt());
        e.setSectorEnt(dto.sectorEnt());
        e.setSubsecEnt(dto.subsecEnt());
        e.setAreaEnt(dto.areaEnt());
        e.setSubareaEnt(dto.subareaEnt());
        e.setNivelInst(dto.nivelInst());
        e.setEstructura(dto.estructuraId() == null ? null : estructuraEntidadRepository.getReferenceById(dto.estructuraId()));
    }
}
