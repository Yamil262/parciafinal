package com.sis414.parciafinal.service;

import com.sis414.parciafinal.dto.EstructuraEntidadDto;
import com.sis414.parciafinal.entity.EstructuraEntidad;
import com.sis414.parciafinal.repository.EstructuraEntidadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstructuraEntidadService {

    private final EstructuraEntidadRepository repository;

    public EstructuraEntidadService(EstructuraEntidadRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EstructuraEntidadDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public EstructuraEntidadDto findById(Long id) {
        return toDto(getOrThrow(id));
    }

    @Transactional
    public EstructuraEntidadDto create(EstructuraEntidadDto dto) {
        EstructuraEntidad entity = new EstructuraEntidad();
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public EstructuraEntidadDto update(Long id, EstructuraEntidadDto dto) {
        EstructuraEntidad entity = getOrThrow(id);
        apply(dto, entity);
        return toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    public EstructuraEntidad getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EstructuraEntidad no encontrado: " + id));
    }

    public EstructuraEntidadDto toDto(EstructuraEntidad e) {
        return new EstructuraEntidadDto(
                e.getId(),
                e.getGestion(),
                e.getSectorEnt(),
                e.getSubsecEnt(),
                e.getAreaEnt(),
                e.getSubareaEnt(),
                e.getNivelInst(),
                e.getDesEstruc(),
                e.getSiglaEstru(),
                e.getApropiable()
        );
    }

    private void apply(EstructuraEntidadDto dto, EstructuraEntidad e) {
        e.setGestion(dto.gestion());
        e.setSectorEnt(dto.sectorEnt());
        e.setSubsecEnt(dto.subsecEnt());
        e.setAreaEnt(dto.areaEnt());
        e.setSubareaEnt(dto.subareaEnt());
        e.setNivelInst(dto.nivelInst());
        e.setDesEstruc(dto.desEstruc());
        e.setSiglaEstru(dto.siglaEstru());
        e.setApropiable(dto.apropiable());
    }
}
